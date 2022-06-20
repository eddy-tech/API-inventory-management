package com.inventor.management.services.impl;

import com.inventor.management.entities.Article;
import com.inventor.management.entities.Customer;
import com.inventor.management.entities.CustomerOrder;
import com.inventor.management.entities.CustomerOrderLine;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.mapper.StockMapperImpl;
import com.inventor.management.repository.ArticleRepository;
import com.inventor.management.repository.CustomerOrderLineRepository;
import com.inventor.management.repository.CustomerOrderRepository;
import com.inventor.management.repository.CustomerRepository;
import com.inventor.management.services.interfaces.CustomerOrderService;
import com.inventor.management.validators.CustomerOrderValidator;
import com.inventor.management.dto.CustomerOrderDto;
import com.inventor.management.exceptions.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private CustomerRepository customerRepository;
    private CustomerOrderRepository customerOrderRepository;
    private CustomerOrderLineRepository customerOrderLineRepository;
    private ArticleRepository articleRepository;
    private StockMapperImpl dtoMapper;

    @Autowired
    public CustomerOrderServiceImpl(CustomerRepository customerRepository, CustomerOrderRepository customerOrderRepository,
                                    CustomerOrderLineRepository customerOrderLineRepository, ArticleRepository articleRepository,
                                    StockMapperImpl dtoMapper) {
        this.customerRepository = customerRepository;
        this.customerOrderRepository = customerOrderRepository;
        this.customerOrderLineRepository = customerOrderLineRepository;
        this.articleRepository = articleRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public CustomerOrderDto saveCustomerOrder(CustomerOrderDto customerOrderDto) {
        List<String> errors = CustomerOrderValidator.validate(customerOrderDto);
        if(!errors.isEmpty()){
            log.error("Customer Order is invalid");
            throw new InvalidEntityException("Customer order is invalid", ErrorCodes.CUSTOMER_ORDER_NOT_VALID,errors);
        }

        Customer customer = customerRepository.findById(customerOrderDto.getCustomerDto().getId())
                .orElseThrow(()-> new EntityNotFoundException("Nothing customer with ID ="+customerOrderDto.getCustomerDto().getId()+"was found in database",ErrorCodes.CUSTOMER_NOT_FOUND));

        List<String> articleErrors = new ArrayList<>();

        if(customerOrderDto.getCustomerOrderLinesDto() != null){
            customerOrderDto.getCustomerOrderLinesDto().forEach(customerOrderLine -> {
                if(customerOrderLine.getArticleDto() != null){
                    Optional<Article> article = articleRepository.findById(customerOrderLine.getArticleDto().getId());
                    if(article.isEmpty()){
                        articleErrors.add("Article with ID ="+customerOrderLine.getArticleDto().getId()+ "not exist");
                    }
                } else {
                    articleErrors.add("Impossible to save customer order with an article null");
                }
            } );
        }

        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("Article not exist in DataBase",ErrorCodes.ARTICLES_NOT_FOUND,articleErrors);
        }

        CustomerOrder customerOrder = dtoMapper.fromCustomerOrderDto(customerOrderDto);
        CustomerOrder savedCustomerOrder = customerOrderRepository.save(customerOrder);

        if(customerOrderDto.getCustomerOrderLinesDto() != null){
            customerOrderDto.getCustomerOrderLinesDto().forEach(customerOrderLine->{
                CustomerOrderLine customerOrderLines = dtoMapper.fromCustomerOrderLineDto(customerOrderLine);
                customerOrderLines.setCustomerOrder(savedCustomerOrder); // ASSIGN CUSTOMER ORDER SAVE IN EACH CUSTOMER ORDER LINE BECAUSE WE DON'T SAVE CUSTOMER ORDER LINE WITHOUT CUSTOMER ORDER
                customerOrderLineRepository.save(customerOrderLines);
            });
        }

        return dtoMapper.fromCustomerOrder(savedCustomerOrder);
    }

    @Override
    public CustomerOrderDto updateCustomerOrder(CustomerOrderDto customerOrderDto) {
        List<String> errors = CustomerOrderValidator.validate(customerOrderDto);
        if(!errors.isEmpty()){
            log.error("Customer order is invalid",errors);
            throw  new InvalidEntityException("Customer order is invalid",ErrorCodes.CUSTOMER_ORDER_NOT_VALID,errors);
        }

        Customer customer = customerRepository.findById(customerOrderDto.getCustomerDto().getId())
                .orElseThrow(()->new EntityNotFoundException("Nothing customer with ID ="+customerOrderDto.getCustomerDto().getId()+"was found in databse",ErrorCodes.CUSTOMER_NOT_FOUND));

        List<String> articleErrors = new ArrayList<>();

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

        CustomerOrder updateCustomerOrder = customerOrderRepository.save(dtoMapper.fromCustomerOrderDto(customerOrderDto));

        if(customerOrderDto.getCustomerOrderLinesDto()!= null){
            customerOrderDto.getCustomerOrderLinesDto().forEach(customerOrderLineDto -> {
                CustomerOrderLine customerOrderLine = dtoMapper.fromCustomerOrderLineDto(customerOrderLineDto);
                customerOrderLine.setCustomerOrder(updateCustomerOrder);
                customerOrderLineRepository.save(customerOrderLine);
            });
        }

        return dtoMapper.fromCustomerOrder(updateCustomerOrder);
    }

    @Override
    public CustomerOrderDto getCustomerOrder(Long id) {
        if(id == null){
            log.error("Customer order is NULL");
            return null;
        }

        CustomerOrder customerOrder = customerOrderRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Nothing Customer order was found with ID ="+id,ErrorCodes.CUSTOMER_ORDER_NOT_FOUND));
        return dtoMapper.fromCustomerOrder(customerOrder);
    }

    @Override
    public CustomerOrderDto getCodeCustomerOrder(String codeCustomerOrder) {
        if(!StringUtils.hasLength(codeCustomerOrder)){
            log.error("Customer order is NULL");
            throw new EntityNotFoundException("Nothing code customer order with ID ="+codeCustomerOrder+"was found in database",ErrorCodes.CUSTOMER_ORDER_NOT_FOUND);
        }

        CustomerOrder customerOrder = customerOrderRepository.findCustomerOrderByCodeOrderCustomer(codeCustomerOrder);
        return dtoMapper.fromCustomerOrder(customerOrder);
    }

    @Override
    public List<CustomerOrderDto> listCustomerOrder() {
        List<CustomerOrder> customerOrderList = customerOrderRepository.findAll();
        List<CustomerOrderDto> customerOrderDtoList = customerOrderList.stream()
                .map(customerOrder -> dtoMapper.fromCustomerOrder(customerOrder)).collect(Collectors.toList());

        return customerOrderDtoList;
    }

    @Override
    public void deleteCustomerOrder(Long id) {
        if(id == null){
            log.error("Customer order ID is NULL");
            return;
        }
        customerOrderRepository.deleteById(id);
    }
}
