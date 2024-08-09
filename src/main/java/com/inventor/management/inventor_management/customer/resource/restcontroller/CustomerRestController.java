package com.inventor.management.inventor_management.customer.resource.restcontroller;

import com.inventor.management.inventor_management.customer.dto.CustomerDto;
import com.inventor.management.inventor_management.customer.service.CustomerService;
import com.inventor.management.inventor_management.customer.resource.api.CustomerApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.inventor.management.inventor_management.customer.roots.CustomerEndPoint.CUSTOMER_ENDPOINT;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(CUSTOMER_ENDPOINT)
public class CustomerRestController implements CustomerApi {
    private final CustomerService customerService;

    @Override
    public ResponseEntity<CustomerDto> saveCustomer(CustomerDto customerDto) {
        return ResponseEntity.status(CREATED)
                .body(customerService.saveCustomer(customerDto));
    }

    @Override
    public ResponseEntity<CustomerDto> updateCustomer(CustomerDto customerDto, Long customerId) {
        return ResponseEntity.ok(customerService.updateCustomer(customerDto, customerId));
    }

    @Override
    public ResponseEntity<CustomerDto> getCustomer(Long id) {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @Override
    public ResponseEntity<List<CustomerDto>> listCustomer() {
        return ResponseEntity.ok(customerService.listCustomer());
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
