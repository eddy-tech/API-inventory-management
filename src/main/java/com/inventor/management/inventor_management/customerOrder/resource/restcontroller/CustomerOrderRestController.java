package com.inventor.management.inventor_management.customerOrder.resource.restcontroller;

import com.inventor.management.inventor_management.customerOrder.dto.CustomerOrderDto;
import com.inventor.management.inventor_management.customerOrder.dto.CustomerOrderRequest;
import com.inventor.management.inventor_management.customerOrderLine.dto.CustomerOrderLineDto;
import com.inventor.management.core.enums.StateOrder;
import com.inventor.management.inventor_management.customerOrder.service.CustomerOrderService;
import com.inventor.management.inventor_management.customerOrder.resource.api.CustomerOrderApi;
import com.inventor.management.inventor_management.customerOrder.roots.CustomerOrderEndPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(CustomerOrderEndPoint.CUSTOMER_ORDER_ENDPOINT)
@RequiredArgsConstructor
public class CustomerOrderRestController implements CustomerOrderApi {
    private final CustomerOrderService customerOrderService;

    @Override
    public ResponseEntity<CustomerOrderDto> saveCustomerOrder(CustomerOrderRequest customerOrderRequest) {
        return ResponseEntity.ok(customerOrderService.saveCustomerOrder(customerOrderRequest));
    }

    @Override
    public ResponseEntity<CustomerOrderDto> updateCustomerOrder(
            CustomerOrderRequest customerOrderRequest, Long customerOrderId
    ) {
        return ResponseEntity.ok(customerOrderService.updateCustomerOrder(customerOrderRequest, customerOrderId));
    }

    @Override
    public ResponseEntity<CustomerOrderDto> updateStateOrder(Long orderId, StateOrder stateOrder) {
        return ResponseEntity.ok(customerOrderService.updateStateOrder(orderId, stateOrder));
    }

    @Override
    public ResponseEntity<CustomerOrderDto> updateQuantityOrdered(Long orderId, Long orderLineId, BigDecimal quantity) {
        return ResponseEntity.ok(customerOrderService.updateQuantityOrdered(orderId,orderLineId,quantity));
    }

    @Override
    public ResponseEntity<CustomerOrderDto> updateCustomer(Long orderId, Long customerId) {
        return ResponseEntity.ok(customerOrderService.updateCustomer(orderId,customerId));
    }

    @Override
    public ResponseEntity<CustomerOrderDto> updateArticle(Long orderId, Long orderLineId, Long articleId) {
        return ResponseEntity.ok(customerOrderService.updateArticle(orderId,orderLineId,articleId));
    }

    @Override
    public ResponseEntity<CustomerOrderDto> getCustomerOrder(Long id) {
        return ResponseEntity.ok(customerOrderService.getCustomerOrder(id));
    }

    @Override
    public ResponseEntity<CustomerOrderDto> getCodeCustomerOrder(String codeCustomerOrder) {
        return ResponseEntity.ok(customerOrderService.getCodeCustomerOrder(codeCustomerOrder));
    }

    @Override
    public ResponseEntity<List<CustomerOrderDto>> listCustomerOrder() {
        return ResponseEntity.ok(customerOrderService.listCustomerOrder());
    }

    @Override
    public ResponseEntity<List<CustomerOrderLineDto>> findAllCustomerOrdersLinesByCustomerOrderId(Long orderId) {
        return ResponseEntity.ok(customerOrderService.findAllCustomerOrdersLinesByCustomerOrderId(orderId));
    }

    @Override
    public ResponseEntity<Void> deleteCustomerOrder(Long id) {
        customerOrderService.deleteCustomerOrder(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CustomerOrderDto> deleteArticle(Long orderId, Long orderLineId) {
        return ResponseEntity.ok(customerOrderService.deleteArticle(orderId,orderLineId));
    }
}
