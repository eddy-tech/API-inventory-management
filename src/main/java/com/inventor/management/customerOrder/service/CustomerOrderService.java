package com.inventor.management.customerOrder.service;

import com.inventor.management.customerOrder.dto.CustomerOrderDto;
import com.inventor.management.customerOrderLine.dto.CustomerOrderLineDto;
import com.inventor.management.core.enums.StateOrder;

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
