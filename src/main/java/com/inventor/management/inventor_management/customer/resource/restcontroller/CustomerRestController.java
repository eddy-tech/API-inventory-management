package com.inventor.management.inventor_management.customer.resource.restcontroller;

import com.inventor.management.inventor_management.customer.dto.CustomerDto;
import com.inventor.management.inventor_management.customer.service.CustomerService;
import com.inventor.management.inventor_management.customer.resource.api.CustomerApi;
import com.inventor.management.inventor_management.customer.roots.CustomerEndPoint;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(CustomerEndPoint.CUSTOMER_ENDPOINT)
public class CustomerRestController implements CustomerApi {
    private final CustomerService customerService;

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return customerService.saveCustomer(customerDto);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, Long customerId) {
        return customerService.updateCustomer(customerDto, customerId);
    }

    @Override
    public CustomerDto getCustomer(Long id) {
        return customerService.getCustomer(id);
    }

    @Override
    public List<CustomerDto> listCustomer() {
        return customerService.listCustomer();
    }

    @Override
    public void deleteCustomer(Long id) {
        customerService.deleteCustomer(id);
    }
}
