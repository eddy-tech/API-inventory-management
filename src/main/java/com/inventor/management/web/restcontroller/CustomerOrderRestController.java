package com.inventor.management.web.restcontroller;

import com.inventor.management.dto.CustomerOrderDto;
import com.inventor.management.services.interfaces.CustomerOrderService;
import com.inventor.management.web.api.CustomerOrderApi;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.ok(customerOrderService.saveCustomerOrder(customerOrderDto));
    }

    @Override
    public ResponseEntity<CustomerOrderDto> updateCustomerOrder(CustomerOrderDto customerOrderDto) {
        return ResponseEntity.ok(customerOrderService.saveCustomerOrder(customerOrderDto));
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
    public ResponseEntity deleteCustomerOrder(Long id) {
        customerOrderService.deleteCustomerOrder(id);
        return ResponseEntity.ok().build();
    }
}
