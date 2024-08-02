package com.inventor.management.customer.resource.restcontroller;

import com.inventor.management.customer.dto.CustomerDto;
import com.inventor.management.customer.service.CustomerService;
import com.inventor.management.customer.resource.api.CustomerApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.inventor.management.customer.roots.CustomerEndPoint.CUSTOMER_ENDPOINT;

@RestController
@AllArgsConstructor
@RequestMapping(CUSTOMER_ENDPOINT)
public class CustomerRestController implements CustomerApi {

    private final CustomerService customerService;

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return customerService.saveCustomer(customerDto);
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) {
        return customerService.updateCustomer(customerDto);
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
