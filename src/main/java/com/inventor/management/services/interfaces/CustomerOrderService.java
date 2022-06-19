package com.inventor.management.services.interfaces;

import com.inventor.management.dto.CustomerOrderDto;

import java.util.List;

public interface CustomerOrderService {
    CustomerOrderDto saveCustomerOrder (CustomerOrderDto customerOrderDto);

    CustomerOrderDto updateCustomerOrder (CustomerOrderDto customerOrderDto);

    CustomerOrderDto getCustomerOrder (Long id);

    CustomerOrderDto getCodeCustomerOrder (String codeCustomerOrder);

    List<CustomerOrderDto> listCustomerOrder ();

    void deleteCustomerOrder (Long id);
}
