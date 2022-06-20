package com.inventor.management.web.restcontroller;

import com.inventor.management.dto.CustomerDto;
import com.inventor.management.services.interfaces.CustomerService;
import com.inventor.management.web.api.CustomerApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerRestController implements CustomerApi {

    private CustomerService customerService;

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return customerService.saveCustomer(customerDto);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
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
