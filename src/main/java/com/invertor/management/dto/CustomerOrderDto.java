package com.invertor.management.dto;

import com.invertor.management.entities.CustomerOrderLine;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CustomerOrderDto {

    private Long id;

    private String codeOrderCustomer;

    private Instant dateOrder;

    private CustomerDto customerDto;

    private List<CustomerOrderLineDto> customerOrderLinesDto;

}