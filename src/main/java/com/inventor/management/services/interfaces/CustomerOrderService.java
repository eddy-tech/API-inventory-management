package com.inventor.management.services.interfaces;

import com.inventor.management.dto.CustomerOrderDto;
import com.inventor.management.dto.CustomerOrderLineDto;
import com.inventor.management.enums.StateOrder;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerOrderService {
    CustomerOrderDto saveCustomerOrder (CustomerOrderDto customerOrderDto);
    CustomerOrderDto updateCustomerOrder (CustomerOrderDto customerOrderDto);
    CustomerOrderDto updateQuantityOrdered (Long orderId, Long orderLineId, BigDecimal quantity);
    CustomerOrderDto updateStateOrder (Long orderId, StateOrder stateOrder);
    CustomerOrderDto updateCustomer (Long orderId, Long customerId);
    CustomerOrderDto updateArticle (Long orderId, Long orderLineId , Long articleId);
    CustomerOrderDto getCustomerOrder (Long id);
    CustomerOrderDto getCodeCustomerOrder (String codeCustomerOrder);
    List<CustomerOrderDto> listCustomerOrder ();
    List<CustomerOrderLineDto> findAllCustomerOrdersLinesByCustomerOrderId (Long orderId);
    void deleteCustomerOrder (Long id);
    // DELETE CUSTOMER ORDER LINE
    CustomerOrderDto deleteArticle (Long orderId, Long orderLineId);
}
