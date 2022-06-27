package com.inventor.management.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class CustomerOrderDto {

    private Long id;

    private String codeOrderCustomer;

    private Instant dateOrder;

    private CustomerDto customerDto;

    private Long id_enterprise;

    private List<CustomerOrderLineDto> customerOrderLinesDto;

}
