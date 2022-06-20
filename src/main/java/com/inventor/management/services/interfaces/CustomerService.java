package com.inventor.management.services.interfaces;


import com.inventor.management.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto saveCustomer (CustomerDto customerDto);

    CustomerDto updateCustomer (CustomerDto customerDto);

    CustomerDto getCustomer (Long id);

    List<CustomerDto> listCustomer ();

    void deleteCustomer (Long id);
}
