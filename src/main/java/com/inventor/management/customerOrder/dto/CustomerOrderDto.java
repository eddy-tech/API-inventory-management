package com.inventor.management.customerOrder.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.customer.dto.CustomerDto;
import com.inventor.management.customerOrderLine.dto.CustomerOrderLineDto;
import com.inventor.management.core.enums.StateOrder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class CustomerOrderDto {

    private Long id;

    private String codeOrderCustomer;

    private Instant dateOrder;
    private StateOrder stateOrder;

    private CustomerDto customerDto;

    private Long id_enterprise;

    @JsonIgnore
    private List<CustomerOrderLineDto> customerOrderLinesDto;

    // METHOD TO CHECK IF ORDER STATE HAS BEEN DELIVERED OR NO
    public boolean isOrderDelivered() {
        return stateOrder.equals(this.stateOrder);
    }

}
