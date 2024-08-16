package com.inventor.management.inventor_management.customerOrder.mapper;

import com.inventor.management.inventor_management.customer.entity.Customer;
import com.inventor.management.inventor_management.customer.mapper.CustomerMapper;
import com.inventor.management.inventor_management.customerOrder.dto.CustomerOrderDto;
import com.inventor.management.inventor_management.customerOrder.dto.CustomerOrderRequest;
import com.inventor.management.inventor_management.customerOrder.entity.CustomerOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CustomerOrderMapper {
    private final CustomerMapper customerMapper;
    public CustomerOrder fromCustomerOrderDto (CustomerOrderRequest customerOrderRequest, Customer customer){
        return CustomerOrder.builder()
                .dateOrder(customerOrderRequest.dateOrder())
                .stateOrder(customerOrderRequest.stateOrder())
                .customer(customer)
                .creationTime(Instant.now())
                .build();
    }

    public CustomerOrderDto fromCustomerOrder (CustomerOrder customerOrder){
        return CustomerOrderDto.builder()
                .id(customerOrder.getId())
                .codeOrderCustomer(customerOrder.getCodeCustomerOrder())
                .dateOrder(customerOrder.getDateOrder())
                .codeOrderCustomer(customerOrder.getCodeCustomerOrder())
                .stateOrder(customerOrder.getStateOrder())
                .customerDto(customerMapper.fromCustomer(customerOrder.getCustomer()))
                .id_enterprise(customerOrder.getCustomer().getEnterprise().getId())
                .build();
    }

    public CustomerOrder toCustomerOrder (CustomerOrderDto customerOrderDto){
        return CustomerOrder.builder()
                .dateOrder(customerOrderDto.getDateOrder())
                .stateOrder(customerOrderDto.getStateOrder())
                .customer(customerMapper.toCustomer(customerOrderDto.getCustomerDto()))
                .creationTime(Instant.now())
                .build();
    }
}
