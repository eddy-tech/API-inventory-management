package com.inventor.management.inventor_management.customer.mapper;

import com.inventor.management.inventor_management.customer.dto.CustomerDto;
import com.inventor.management.inventor_management.customerOrder.dto.CustomerOrderDto;
import com.inventor.management.inventor_management.customerOrderLine.dto.CustomerOrderLineDto;
import com.inventor.management.inventor_management.customer.entity.Customer;
import com.inventor.management.inventor_management.customerOrder.entity.CustomerOrder;
import com.inventor.management.inventor_management.customerOrderLine.entity.CustomerOrderLine;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public CustomerDto fromCustomer (Customer customer){
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer,customerDto);
        return customerDto;
    }

    public Customer fromCustomerDto (CustomerDto customerDto){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto,customer);
        return customer;
    }
}
