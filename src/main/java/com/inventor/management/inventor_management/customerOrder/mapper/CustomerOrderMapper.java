package com.inventor.management.inventor_management.customerOrder.mapper;

import com.inventor.management.inventor_management.customerOrder.dto.CustomerOrderDto;
import com.inventor.management.inventor_management.customerOrder.dto.CustomerOrderRequest;
import com.inventor.management.inventor_management.customerOrder.entity.CustomerOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderMapper {
    public CustomerOrder fromCustomerOrderDto (CustomerOrderRequest customerOrderRequest){
        CustomerOrder customerOrder = new CustomerOrder();
        BeanUtils.copyProperties(customerOrderRequest,customerOrder);
        return customerOrder;
    }

    public CustomerOrderDto fromCustomerOrder (CustomerOrder customerOrder){
        CustomerOrderDto customerOrderDto = new CustomerOrderDto();
        BeanUtils.copyProperties(customerOrderDto,customerOrder);
        return customerOrderDto;
    }

    public CustomerOrder toCustomerOrder (CustomerOrderDto customerOrderDto){
        CustomerOrder customerOrder = new CustomerOrder();
        BeanUtils.copyProperties(customerOrder,customerOrderDto);
        return customerOrder;
    }
}
