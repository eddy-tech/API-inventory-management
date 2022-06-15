package com.invertor.management.services.interfaces;

import com.invertor.management.dto.CustomerOrderDto;
import com.invertor.management.dto.UserDto;

import java.util.List;

public interface CustomerOrderService {
    CustomerOrderDto saveCustomerOrder (CustomerOrderDto customerOrderDto);

    CustomerOrderDto updateCustomerOrder (CustomerOrderDto customerOrderDto);

    CustomerOrderDto getCustomerOrder (Long id);

    CustomerOrderDto getCodeCustomerOrder (String codeCustomerOrder);

    List<CustomerOrderDto> listCustomerOrder ();

    void deleteCustomerOrder (Long id);
}
