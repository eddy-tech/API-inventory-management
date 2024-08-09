package com.inventor.management.inventor_management.customerOrder.service.impl;

import com.inventor.management.core.validator.ObjectValidator;
import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.article.mapper.ArticleMapper;
import com.inventor.management.inventor_management.article.repository.ArticleRepository;
import com.inventor.management.inventor_management.customer.mapper.CustomerMapper;
import com.inventor.management.inventor_management.customerOrder.dto.CustomerOrderRequest;
import com.inventor.management.inventor_management.customerOrder.mapper.CustomerOrderMapper;
import com.inventor.management.inventor_management.customerOrderLine.dto.CustomerOrderLineDto;
import com.inventor.management.inventor_management.customerOrderLine.mapper.CustomerOrderLineMapper;
import com.inventor.management.inventor_management.stockMovement.dto.StockMovementDto;
import com.inventor.management.inventor_management.customer.entity.Customer;
import com.inventor.management.inventor_management.customerOrder.entity.CustomerOrder;
import com.inventor.management.inventor_management.customerOrderLine.entity.CustomerOrderLine;
import com.inventor.management.inventor_management.core.enums.SourceStockMovement;
import com.inventor.management.inventor_management.core.enums.StateOrder;
import com.inventor.management.inventor_management.core.enums.TypeMoveStock;
import com.inventor.management.core.exceptions.EntityNotFoundException;
import com.inventor.management.core.exceptions.InvalidEntityException;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.inventor_management.customerOrderLine.repository.CustomerOrderLineRepository;
import com.inventor.management.inventor_management.customerOrder.repository.CustomerOrderRepository;
import com.inventor.management.inventor_management.customer.repository.CustomerRepository;
import com.inventor.management.inventor_management.customerOrder.service.CustomerOrderService;
import com.inventor.management.inventor_management.stockMovement.service.StockMovementService;
import com.inventor.management.inventor_management.customerOrder.dto.CustomerOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.inventor.management.inventor_management.core.utils.Constants.EDIT_STATE_ORDER;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {
    List<String> articleErrors = new ArrayList<>();
    private final CustomerRepository customerRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final CustomerOrderLineRepository customerOrderLineRepository;
    private final ArticleRepository articleRepository;
    private final StockMovementService stockMovementService;
    private final CustomerOrderMapper customerOrderMapper;
    private final CustomerOrderLineMapper customerOrderLineMapper;
    private final ArticleMapper articleMapper;
    private final CustomerMapper customerMapper;
    private final ObjectValidator validator;


    private void checkArticleId (Long idArticle){
        if(idArticle == null){
            log.error("ID of new is NULL");
            throw new InvalidOperationException(EDIT_STATE_ORDER);
        }
    }

    private Optional<CustomerOrderLine> findCustomerOrderLine (Long orderLineId){
        return Optional.ofNullable(customerOrderLineRepository.findById(orderLineId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Nothing customer order line has been found with ID =" + orderLineId)
                ));
    }

    private Customer findCustomer (Long customerId){
        return customerRepository.findById(customerId)
                .orElseThrow(()->new EntityNotFoundException(
                        "Nothing customer has been found with ID =" +customerId)
                );
    }

    private Article findArticle (Long articleId){
        return articleRepository.findById(articleId)
                .orElseThrow(()-> new EntityNotFoundException("Nothing article has been found with ID ="+ articleId));
    }

    private CustomerOrder findCustomerOrder (Long customerOrderId){
        return customerOrderRepository.findById(customerOrderId)
                .orElseThrow(()->new EntityNotFoundException(
                        "Nothing Customer order has been found with ID ="+ customerOrderId)
                );
    }

    @Override
    public CustomerOrderDto saveCustomerOrder(CustomerOrderRequest customerOrderRequest) {
        validator.validate(customerOrderRequest);
        customerRepository.findById(customerOrderRequest.customerId())
                .orElseThrow(()-> new EntityNotFoundException(
                        "Nothing customer order with ID ="+ customerOrderRequest.customerId() +
                        "has been found in database")
                );

        if(customerOrderRequest.customerOrderLinesDto() != null){
            customerOrderRequest.customerOrderLinesDto().forEach(customerOrderLine -> {
                if(customerOrderLine.getArticleDto() != null){
                    var article = articleRepository.findById(customerOrderLine.getArticleDto().getId());
                    if(article.isEmpty()){
                        articleErrors.add("Article with ID ="+ customerOrderLine.getArticleDto().getId() + "not exist");
                    }
                } else {
                    articleErrors.add("Unable to save customer order with an article null");
                }
            });
        }

        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("Article not exist in DataBase");
        }

        var savedCustomerOrder = customerOrderRepository.save(
                customerOrderMapper.fromCustomerOrderDto(customerOrderRequest)
        );

        if(customerOrderRequest.customerOrderLinesDto() != null){
            customerOrderRequest.customerOrderLinesDto().forEach(customerOrderLine->{
                CustomerOrderLine customerOrderLines = customerOrderLineMapper.fromCustomerOrderLineDto(customerOrderLine);
//ASSIGN CUSTOMER ORDER SAVE IN EACH CUSTOMER ORDER LINE BECAUSE WE DON'T SAVE CUSTOMER ORDER LINE WITHOUT CUSTOMER ORDER
                customerOrderLines.setCustomerOrder(savedCustomerOrder);
                customerOrderLineRepository.save(customerOrderLines);
            });
        }

        return customerOrderMapper.fromCustomerOrder(savedCustomerOrder);
    }

    @Override
    public CustomerOrderDto updateCustomerOrder(CustomerOrderRequest customerOrderRequest, Long id) {
        validator.validate(customerOrderRequest);
        customerRepository.findById(customerOrderRequest.customerId())
                .orElseThrow(()->new EntityNotFoundException(
                        "Nothing customer with ID ="+ customerOrderRequest.customerId() + "has been found in database")
                );

        if(id != null && customerOrderRequest.isOrderDelivered())
            throw new InvalidOperationException("Unable to update provider order");

        if(customerOrderRequest.customerOrderLinesDto() != null){
            customerOrderRequest.customerOrderLinesDto().forEach(customerOrderLineDto -> {
                if(customerOrderLineDto.getArticleDto() != null){
                    var article = articleRepository.findById(customerOrderLineDto.getArticleDto().getId());
                    if(article.isEmpty()){
                        articleErrors.add(
                                "Nothing article with ID ="+customerOrderLineDto.getArticleDto().getId()+
                                        "was found in database"
                        );
                    } else {
                        articleErrors.add("Impossible to update with an article null");
                    }
                }
            });
        }

        var updateCustomerOrder = customerOrderRepository.save(
                customerOrderMapper.fromCustomerOrderDto(customerOrderRequest)
        );

        if(customerOrderRequest.customerOrderLinesDto()!= null){
            customerOrderRequest.customerOrderLinesDto().forEach(customerOrderLineDto -> {
                var customerOrderLine = customerOrderLineMapper.fromCustomerOrderLineDto(customerOrderLineDto);
                customerOrderLine.setCustomerOrder(updateCustomerOrder);
                customerOrderLineRepository.save(customerOrderLine);
            });
        }

        return customerOrderMapper.fromCustomerOrder(updateCustomerOrder);
    }

    private void checkOrderId (Long orderId){
        if(orderId == null) {
            log.error("customer order ID is null");
            throw new InvalidOperationException("Unable to edit quantity ordered with null ID");
        }
    }

    private void checkOrderLineId (Long orderLineId) {
        if(orderLineId == null) {
            log.error("customer order Line ID is null");
            throw new InvalidOperationException("Unable to edit quantity ordered with null ID order line");
        }
    }

    private CustomerOrderDto checkStateOrder(Long orderId){
        if(getCustomerOrder(orderId).isOrderDelivered())
            throw new InvalidOperationException("Unable to edit state order with null ID");

        return getCustomerOrder(orderId);
    }

    @Override
    public CustomerOrderDto updateQuantityOrdered(Long orderId, Long orderLineId, BigDecimal quantity) {
        checkOrderId(orderId);
        checkOrderLineId(orderLineId);
      if(quantity == null || quantity.compareTo(BigDecimal.ZERO) == 0) {
            log.error("quantity of customer order is null");
            throw new InvalidOperationException("Unable to edit quantity ordered with null quantity or 0");
        }

        var customerOrder = checkStateOrder(orderId);
        var customerOrderLineOptional = findCustomerOrderLine(orderLineId);

        var customerOrderLine = customerOrderLineOptional.get();
        customerOrderLine.setQuantity(quantity);
        customerOrderLineRepository.save(customerOrderLine);

        return customerOrder;
    }

    @Override
    public CustomerOrderDto updateStateOrder(Long orderId, StateOrder stateOrder) {
        checkOrderId(orderId);

        if(!StringUtils.hasLength(String.valueOf(stateOrder))) {
            log.error("customer state order is NULL");
            throw new InvalidOperationException("Unable to edit state order with state NULL");
        }

        var orderDto = checkStateOrder(orderId);
        orderDto.setStateOrder(stateOrder);
        var customerOrder = customerOrderRepository.save(customerOrderMapper.toCustomerOrder(orderDto));

        // Carry out the release of stock when the customer order is delivered: Important!!!
        if(orderDto.isOrderDelivered()){
             updateStockMovementCustomer(orderId);
        }

        return customerOrderMapper.fromCustomerOrder(customerOrder);
    }

    @Override
    public CustomerOrderDto updateCustomer(Long orderId, Long customerId) {
        checkOrderId(orderId);

       if(customerId == null) {
            log.error("customer ID is null");
            throw new InvalidOperationException("Unable to edit state order with null ID");
        }

       var customerOrder = this.checkStateOrder(orderId);
       var customerOptional = this.findCustomer(customerId);
       var customer = customerMapper.fromCustomer(customerOptional);

       customerOrder.setCustomerDto(customer);

        return customerOrderMapper.fromCustomerOrder(
                customerOrderRepository.save(
                        customerOrderMapper.toCustomerOrder(customerOrder)
                )
        );
    }

    @Override
    public CustomerOrderDto updateArticle(Long orderId, Long orderLineId, Long articleId) {
        checkOrderId(orderId);
        checkOrderLineId(orderLineId);
        checkArticleId(articleId);
        var customerOrder = checkStateOrder(orderId);
        var customerOrderLine = findCustomerOrderLine(orderLineId);
        var articleOptional = findArticle(articleId);

        validator.validate(articleOptional);

        CustomerOrderLine customerOrderLineToSaved = customerOrderLine.get();
        customerOrderLineToSaved.setArticle(articleOptional);
        customerOrderLineRepository.save(customerOrderLineToSaved);

        return customerOrder;
    }

    @Override
    public CustomerOrderDto getCustomerOrder(Long id) {
        if(id == null){
            log.error("Customer order is NULL");
            return null;
        }

        return customerOrderMapper.fromCustomerOrder(this.findCustomerOrder(id));
    }

    @Override
    public CustomerOrderDto getCodeCustomerOrder(String codeCustomerOrder) {
        if(!StringUtils.hasLength(codeCustomerOrder)){
            log.error("Customer order is NULL");
            throw new EntityNotFoundException(
                    "Nothing code customer order with ID ="+ codeCustomerOrder +
                            "was found in database");
        }

        return customerOrderMapper.fromCustomerOrder(
                customerOrderRepository.findByCodeCustomerOrder(codeCustomerOrder)
        );
    }

    @Override
    public List<CustomerOrderDto> listCustomerOrder() {
        return customerOrderRepository.findAll().stream()
                .map(customerOrderMapper::fromCustomerOrder)
                .toList();
    }

    @Override
    public List<CustomerOrderLineDto> findAllCustomerOrdersLinesByCustomerOrderId(Long orderId) {
        return customerOrderLineRepository.findAllByCustomerOrderId(orderId).stream()
                .map(customerOrderLineMapper::fromCustomerOrderLine)
                 .toList();
    }


    @Override
    public void deleteCustomerOrder(Long id) {
        if(id == null){
            log.error("Customer order ID is NULL");
            return;
        }

        List<CustomerOrderLine> customerOrderLineList = customerOrderLineRepository.findAllByCustomerOrderId(id);
        if(!customerOrderLineList.isEmpty()){
            throw new InvalidOperationException("Unable to delete customer order that has already customer order line");
        }
        customerOrderRepository.deleteById(id);
    }

    @Override
    public CustomerOrderDto deleteArticle(Long orderId, Long orderLineId) {
        this.checkOrderId(orderId);
        this.checkOrderLineId(orderLineId);

        var customerOrder = checkStateOrder(orderId);
        // JUST TO CHECK CUSTOMER ORDER LINE AND INFORM THE CLIENT IN CASE IT IS ABSENT
        this.findCustomerOrderLine(orderLineId);
        customerOrderLineRepository.deleteById(orderLineId);

        return customerOrder;
    }

    private void updateStockMovementCustomer (Long orderId){
        List<CustomerOrderLine> customerOrderLineList = customerOrderLineRepository.findAllByCustomerOrderId(orderId);

        customerOrderLineList.forEach(customerOrderLine -> {
            StockMovementDto stockMovement = new StockMovementDto();
            stockMovement.setArticleDto(articleMapper.fromArticleDto(customerOrderLine.getArticle()));
            stockMovement.setDateMovement(Instant.now());
            stockMovement.setTypeMoveStock(TypeMoveStock.EXIT);
            stockMovement.setSourceStockMovement(SourceStockMovement.CUSTOMER_ORDER);
            stockMovement.setQuantity(customerOrderLine.getQuantity());
            stockMovement.setId_enterprise(customerOrderLine.getArticle().getEnterprise().getId());

            stockMovementService.exitStock(stockMovement);
        });
    }
}
