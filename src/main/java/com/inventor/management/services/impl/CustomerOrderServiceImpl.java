package com.inventor.management.services.impl;

import com.inventor.management.dto.CustomerOrderLineDto;
import com.inventor.management.dto.StockMovementDto;
import com.inventor.management.entities.Article;
import com.inventor.management.entities.Customer;
import com.inventor.management.entities.CustomerOrder;
import com.inventor.management.entities.CustomerOrderLine;
import com.inventor.management.enums.SourceStockMovement;
import com.inventor.management.enums.StateOrder;
import com.inventor.management.enums.TypeMoveStock;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.exceptions.InvalidOperationException;
import com.inventor.management.mapper.ArticleMapper;
import com.inventor.management.mapper.CustomerMapper;
import com.inventor.management.repository.ArticleRepository;
import com.inventor.management.repository.CustomerOrderLineRepository;
import com.inventor.management.repository.CustomerOrderRepository;
import com.inventor.management.repository.CustomerRepository;
import com.inventor.management.services.interfaces.CustomerOrderService;
import com.inventor.management.services.interfaces.StockMovementService;
import com.inventor.management.validators.ArticleValidator;
import com.inventor.management.validators.CustomerOrderValidator;
import com.inventor.management.dto.CustomerOrderDto;
import com.inventor.management.exceptions.ErrorCodes;
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
import java.util.stream.Collectors;

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
    private final CustomerMapper customerMapper;
    private final ArticleMapper articleMapper;


    private void checkArticleId (Long idArticle){
        if(idArticle == null){
            log.error("ID of"+"new"+"is NULL");
            throw new InvalidOperationException(
                    "Unable to edit state order with a" + "new" + "article ID null",
                    ErrorCodes.CUSTOMER_ORDER_NOT_MODIFIABLE
            );
        }
    }

    private Optional<CustomerOrderLine> findCustomerOrderLine (Long orderLineId){
        var customerOrderLineOptional = customerOrderLineRepository.findById(orderLineId);
        // CHECKED IF LINE COMMAND EXIST WITH ID PROVIDED
        if(customerOrderLineOptional.isEmpty())
            throw new EntityNotFoundException(
                    "Nothing customer order line has been found with ID ="+orderLineId,
                    ErrorCodes.CUSTOMER_NOT_FOUND
            );
        return customerOrderLineOptional;
    }

    private Customer findCustomer (Long customerId){
        return customerRepository.findById(customerId)
                .orElseThrow(()->new EntityNotFoundException(
                        "Nothing customer has been found with ID ="+customerId,
                        ErrorCodes.CUSTOMER_NOT_FOUND)
                );
    }

    private Article findArticle (Long articleId){
        return articleRepository.findById(articleId)
                .orElseThrow(()->new EntityNotFoundException("Nothing article has been found with ID ="+articleId,
                        ErrorCodes.ARTICLES_NOT_FOUND));
    }

    private CustomerOrder findCustomerOrder (Long customerOrderId){
        return customerOrderRepository.findById(customerOrderId)
                .orElseThrow(()->new EntityNotFoundException(
                        "Nothing Customer order has been found with ID ="+customerOrderId,
                        ErrorCodes.CUSTOMER_ORDER_NOT_FOUND)
                );
    }

    @Override
    public CustomerOrderDto saveCustomerOrder(CustomerOrderDto customerOrderDto) {
        List<String> errors = CustomerOrderValidator.validate(customerOrderDto);
        if(!errors.isEmpty()){
            log.error("Customer Order is invalid");
            throw new InvalidEntityException(
                    "Customer order is invalid", ErrorCodes.CUSTOMER_ORDER_NOT_VALID,errors
            );
        }

        customerRepository.findById(customerOrderDto.getCustomerDto().getId())
                .orElseThrow(()-> new EntityNotFoundException("Nothing customer order with ID ="+customerOrderDto.getCustomerDto().getId()+
                        "has been found in database", ErrorCodes.CUSTOMER_NOT_FOUND));
        /*
        if(customerOrderDto.getId() != null && customerOrderDto.isOrderDelivered()){
            throw new InvalidOperationException("Unable to edit customer order when delivered", ErrorCodes.CUSTOMER_ORDER_NOT_MODIFIABLE);
        }
          */

        if(customerOrderDto.getCustomerOrderLinesDto() != null){
            customerOrderDto.getCustomerOrderLinesDto().forEach(customerOrderLine -> {
                if(customerOrderLine.getArticleDto() != null){
                    Optional<Article> article = articleRepository.findById(customerOrderLine.getArticleDto().getId());
                    if(article.isEmpty()){
                        articleErrors.add("Article with ID ="+customerOrderLine.getArticleDto().getId()+ "not exist");
                    }
                } else {
                    articleErrors.add("Unable to save customer order with an article null");
                }
            } );
        }

        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("Article not exist in DataBase",ErrorCodes.ARTICLES_NOT_FOUND,articleErrors);
        }

        var savedCustomerOrder = customerOrderRepository.save(
                customerMapper.fromCustomerOrderDto(customerOrderDto)
        );

        if(customerOrderDto.getCustomerOrderLinesDto() != null){
            customerOrderDto.getCustomerOrderLinesDto().forEach(customerOrderLine->{
                CustomerOrderLine customerOrderLines = customerMapper.fromCustomerOrderLineDto(customerOrderLine);
                customerOrderLines.setCustomerOrder(savedCustomerOrder); // ASSIGN CUSTOMER ORDER SAVE IN EACH CUSTOMER ORDER LINE BECAUSE
                // WE DON'T SAVE CUSTOMER ORDER LINE WITHOUT CUSTOMER ORDER
                customerOrderLineRepository.save(customerOrderLines);
            });
        }

        return customerMapper.fromCustomerOrder(savedCustomerOrder);
    }

    @Override
    public CustomerOrderDto updateCustomerOrder(CustomerOrderDto customerOrderDto) {
        List<String> errors = CustomerOrderValidator.validate(customerOrderDto);
        if(!errors.isEmpty()){
            log.error("Customer order is invalid" + errors);
            throw new InvalidEntityException(
                    "Customer order is invalid",ErrorCodes.CUSTOMER_ORDER_NOT_VALID, errors
            );
        }

        customerRepository.findById(customerOrderDto.getCustomerDto().getId())
                .orElseThrow(()->new EntityNotFoundException("Nothing customer with ID ="+customerOrderDto.getCustomerDto().getId()+
                        "has been found in database",ErrorCodes.CUSTOMER_NOT_FOUND));

        if(customerOrderDto.getId() != null && customerOrderDto.isOrderDelivered())
            throw new InvalidOperationException("Unable to update provider order",ErrorCodes.CUSTOMER_ORDER_NOT_MODIFIABLE);

        if(customerOrderDto.getCustomerOrderLinesDto() != null){
            customerOrderDto.getCustomerOrderLinesDto().forEach(customerOrderLineDto -> {
                if(customerOrderLineDto.getArticleDto() != null){
                    Optional<Article> article = articleRepository.findById(customerOrderLineDto.getArticleDto().getId());
                    if(article.isEmpty()){
                        articleErrors.add("Nothing article with ID ="+customerOrderLineDto.getArticleDto().getId()+"was found in database");
                    } else {
                        articleErrors.add("Impossible to update with an article null");
                    }
                }
            });
        }

        CustomerOrder updateCustomerOrder = customerOrderRepository.save(customerMapper.fromCustomerOrderDto(customerOrderDto));

        if(customerOrderDto.getCustomerOrderLinesDto()!= null){
            customerOrderDto.getCustomerOrderLinesDto().forEach(customerOrderLineDto -> {
                CustomerOrderLine customerOrderLine = customerMapper.fromCustomerOrderLineDto(customerOrderLineDto);
                customerOrderLine.setCustomerOrder(updateCustomerOrder);
                customerOrderLineRepository.save(customerOrderLine);
            });
        }

        return customerMapper.fromCustomerOrder(updateCustomerOrder);
    }

    private void checkOrderId (Long orderId){
        if(orderId == null) {
            log.error("customer order ID is null");
            throw new InvalidOperationException("Unable to edit quantity ordered with null ID",
                    ErrorCodes.CUSTOMER_ORDER_NOT_MODIFIABLE);
        }
    }

    private void checkOrderLineId (Long orderLineId) {
        if(orderLineId == null) {
            log.error("customer order Line ID is null");
            throw new InvalidOperationException("Unable to edit quantity ordered with null ID order line",
                    ErrorCodes.CUSTOMER_ORDER_NOT_MODIFIABLE);
        }
    }

    private CustomerOrderDto checkStateOrder(Long orderId){
        CustomerOrderDto customerOrder = getCustomerOrder(orderId);
        if(customerOrder.isOrderDelivered()) throw new InvalidOperationException("Unable to edit state order with null ID",
                ErrorCodes.CUSTOMER_ORDER_NOT_MODIFIABLE);

        return customerOrder;
    }

    @Override
    public CustomerOrderDto updateQuantityOrdered(Long orderId, Long orderLineId, BigDecimal quantity) {
        checkOrderId(orderId);
        checkOrderLineId(orderLineId);
      if(quantity == null || quantity.compareTo(BigDecimal.ZERO) == 0) {
            log.error("quantity of customer order is null");
            throw new InvalidOperationException("Unable to edit quantity ordered with null quantity or 0",
                    ErrorCodes.CUSTOMER_ORDER_NOT_MODIFIABLE);
        }

       var customerOrder = checkStateOrder(orderId);
       var customerOrderLineOptional = findCustomerOrderLine(orderLineId);

        CustomerOrderLine customerOrderLine = customerOrderLineOptional.get();
        customerOrderLine.setQuantity(quantity);
        customerOrderLineRepository.save(customerOrderLine);

        return customerOrder;
    }

    @Override
    public CustomerOrderDto updateStateOrder(Long orderId, StateOrder stateOrder) {
        checkOrderId(orderId);

        if(!StringUtils.hasLength(String.valueOf(stateOrder))) {
            log.error("customer state order is NULL");
            throw new InvalidOperationException(
                    "Unable to edit state order with state NULL", ErrorCodes.CUSTOMER_ORDER_NOT_MODIFIABLE
            );
        }

        var orderDto = checkStateOrder(orderId);
        orderDto.setStateOrder(stateOrder);
        var customerOrder = customerMapper.fromCustomerOrderDto(orderDto);
        var savedCustomerOrder = customerOrderRepository.save(customerOrder);

        // EFFECTUER LA SORTIE DU STOCK QUE LORSQUE LA COMMANDE CLIENT EST LIVREE : IMPORTANT !!!!
        if(orderDto.isOrderDelivered()){
        updateStockMovementCustomer(orderId); // METTRE A JOUR MON STOCK
        }

        return customerMapper.fromCustomerOrder(savedCustomerOrder);
    }

    @Override
    public CustomerOrderDto updateCustomer(Long orderId, Long customerId) {
        checkOrderId(orderId);

       if(customerId == null) {
            log.error("customer ID is null");
            throw new InvalidOperationException("Unable to edit state order with null ID",
                    ErrorCodes.CUSTOMER_ORDER_NOT_MODIFIABLE);
        }

       var customerOrder = checkStateOrder(orderId);
       var customerOptional = findCustomer(customerId);
       var customer = customerMapper.fromCustomer(customerOptional);

       customerOrder.setCustomerDto(customer);
       var savedCustomerOrder = customerOrderRepository.save(customerMapper.fromCustomerOrderDto(customerOrder));

        return customerMapper.fromCustomerOrder(savedCustomerOrder);
    }

    @Override
    public CustomerOrderDto updateArticle(Long orderId, Long orderLineId, Long articleId) {
        checkOrderId(orderId);
        checkOrderLineId(orderLineId);
        checkArticleId(articleId);

        var customerOrder = checkStateOrder(orderId); // CHECK STATE OF ORDER
        var customerOrderLine = findCustomerOrderLine(orderLineId);
        var articleOptional = findArticle(articleId);

        List<String> errors = ArticleValidator.validate(articleMapper.fromArticle(articleOptional));
        if(!errors.isEmpty()) throw new InvalidEntityException("Article invalid", ErrorCodes.ARTICLE_NOT_VALID, errors);

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

        return customerMapper.fromCustomerOrder(findCustomerOrder(id));
    }

    @Override
    public CustomerOrderDto getCodeCustomerOrder(String codeCustomerOrder) {
        if(!StringUtils.hasLength(codeCustomerOrder)){
            log.error("Customer order is NULL");
            throw new EntityNotFoundException("Nothing code customer order with ID ="+codeCustomerOrder+"was found in database",ErrorCodes.CUSTOMER_ORDER_NOT_FOUND);
        }

        CustomerOrder customerOrder = customerOrderRepository.findByCodeCustomerOrder(codeCustomerOrder);
        return customerMapper.fromCustomerOrder(customerOrder);
    }

    @Override
    public List<CustomerOrderDto> listCustomerOrder() {
        List<CustomerOrder> customerOrderList = customerOrderRepository.findAll();
        return customerOrderList.stream()
                .map(customerMapper::fromCustomerOrder)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerOrderLineDto> findAllCustomerOrdersLinesByCustomerOrderId(Long orderId) {
         List<CustomerOrderLine> customerOrderLineList = customerOrderLineRepository.findAllByCustomerOrderId(orderId);
        return customerOrderLineList.stream()
                .map(customerMapper::fromCustomerOrderLine)
                 .collect(Collectors.toList());
    }


    @Override
    public void deleteCustomerOrder(Long id) {
        if(id == null){
            log.error("Customer order ID is NULL");
            return;
        }

        List<CustomerOrderLine> customerOrderLineList = customerOrderLineRepository.findAllByCustomerOrderId(id);
        if(!customerOrderLineList.isEmpty()){
            throw new InvalidOperationException("Unable to delete customer order that has already customer order line",
                    ErrorCodes.CUSTOMER_ORDER_ALREADY_IN_USE);
        }
        customerOrderRepository.deleteById(id);
    }

    @Override
    public CustomerOrderDto deleteArticle(Long orderId, Long orderLineId) {
        checkOrderId(orderId);
        checkOrderLineId(orderLineId);

        var customerOrder = checkStateOrder(orderId);
        findCustomerOrderLine(orderLineId);   // JUST TO CHECK CUSTOMER ORDER LINE AND INFORM THE CLIENT IN CASE IT IS ABSENT
        customerOrderLineRepository.deleteById(orderLineId);

        return customerOrder;
    }

    private void updateStockMovementCustomer (Long orderId){
        List<CustomerOrderLine> customerOrderLineList = customerOrderLineRepository.findAllByCustomerOrderId(orderId);

        customerOrderLineList.forEach(customerOrderLine -> {
            StockMovementDto stockMovement = new StockMovementDto();
            stockMovement.setArticleDto(articleMapper.fromArticle(customerOrderLine.getArticle()));
            stockMovement.setDateMovement(Instant.now());
            stockMovement.setTypeMoveStock(TypeMoveStock.EXIT);
            stockMovement.setSourceStockMovement(SourceStockMovement.CUSTOMER_ORDER);
            stockMovement.setQuantity(customerOrderLine.getQuantity());
            stockMovement.setId_enterprise(customerOrderLine.getId_enterprise());

            stockMovementService.exitStock(stockMovement);
        });
    }
}
