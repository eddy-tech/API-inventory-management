package com.inventor.management.inventor_management.customer.service;


import com.inventor.management.inventor_management.customer.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto saveCustomer (CustomerDto customerDto);

    CustomerDto updateCustomer (CustomerDto customerDto, Long id);

    CustomerDto getCustomer (Long id);

    List<CustomerDto> listCustomer ();

    void deleteCustomer (Long id);
}
