package com.inventor.management.mapper;

import com.inventor.management.dto.CustomerDto;
import com.inventor.management.dto.CustomerOrderDto;
import com.inventor.management.dto.CustomerOrderLineDto;
import com.inventor.management.entities.Customer;
import com.inventor.management.entities.CustomerOrder;
import com.inventor.management.entities.CustomerOrderLine;
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

    public CustomerOrderDto fromCustomerOrder (CustomerOrder customerOrder){
        CustomerOrderDto customerOrderDto = new CustomerOrderDto();
        BeanUtils.copyProperties(customerOrder,customerOrderDto);
        return customerOrderDto;
    }

    public CustomerOrder fromCustomerOrderDto (CustomerOrderDto customerOrderDto){
        CustomerOrder customerOrder = new CustomerOrder();
        BeanUtils.copyProperties(customerOrderDto,customerOrder);
        return customerOrder;
    }

    public CustomerOrderLineDto fromCustomerOrderLine (CustomerOrderLine customerOrderLine){
        CustomerOrderLineDto customerOrderLineDto = new CustomerOrderLineDto();
        BeanUtils.copyProperties(customerOrderLine,customerOrderLineDto);
        return customerOrderLineDto;
    }

    public CustomerOrderLine fromCustomerOrderLineDto (CustomerOrderLineDto customerOrderLineDto){
        CustomerOrderLine customerOrderLine = new CustomerOrderLine();
        BeanUtils.copyProperties(customerOrderLineDto,customerOrderLine);
        return customerOrderLine;
    }
}
