package com.inventor.management.inventor_management.customer.service;

import com.inventor.management.inventor_management.customer.dto.CustomerDto;
import com.inventor.management.inventor_management.customer.dto.CustomerRequest;

import java.util.List;

public interface CustomerService {
    CustomerDto saveCustomer (CustomerRequest customerDto);
    CustomerDto updateCustomer (CustomerRequest customerDto, Long id);
    CustomerDto getCustomer (Long id);
    List<CustomerDto> listCustomer ();
    void deleteCustomer (Long id);
}
