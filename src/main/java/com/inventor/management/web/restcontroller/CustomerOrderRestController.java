package com.inventor.management.web.restcontroller;

import com.inventor.management.dto.CustomerOrderDto;
import com.inventor.management.dto.CustomerOrderLineDto;
import com.inventor.management.enums.StateOrder;
import com.inventor.management.services.interfaces.CustomerOrderService;
import com.inventor.management.web.api.CustomerOrderApi;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CustomerOrderRestController implements CustomerOrderApi {
    private CustomerOrderService customerOrderService;

    @Autowired
    public CustomerOrderRestController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @Override
    public ResponseEntity<CustomerOrderDto> saveCustomerOrder(CustomerOrderDto customerOrderDto) {
        return ResponseEntity.ok(customerOrderService.saveCustomerOrder(customerOrderDto)); // ResponseEntity.ok () -> USE BECAUSE WE MANAGE EXCEPTION IN THE SERVICES PACKAGE
    }

    @Override
    public ResponseEntity<CustomerOrderDto> updateCustomerOrder(CustomerOrderDto customerOrderDto) {
        return ResponseEntity.ok(customerOrderService.saveCustomerOrder(customerOrderDto));
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
