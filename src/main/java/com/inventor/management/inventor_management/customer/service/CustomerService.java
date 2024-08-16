package com.inventor.management.inventor_management.customer.service;

import com.inventor.management.inventor_management.customer.dto.CustomerDto;
import com.inventor.management.inventor_management.customer.dto.CustomerRequest;
import com.inventor.management.inventor_management.customer.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerDto saveCustomer (CustomerRequest customerDto);
    CustomerDto updateCustomer (CustomerRequest customerDto, Long id);
    CustomerDto getCustomer (Long id);
    List<CustomerDto> listCustomer ();
    Customer findById(Long id);
    void deleteCustomer (Long id);
}
