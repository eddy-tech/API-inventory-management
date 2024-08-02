package com.inventor.management.inventor_management.customerOrderLine.mapper;

import com.inventor.management.inventor_management.customerOrderLine.dto.CustomerOrderLineDto;
import com.inventor.management.inventor_management.customerOrderLine.entity.CustomerOrderLine;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderLineMapper {
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
