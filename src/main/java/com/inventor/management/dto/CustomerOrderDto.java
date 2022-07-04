package com.inventor.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.enums.StateOrder;
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

    @JsonIgnore
    private List<CustomerOrderLineDto> customerOrderLinesDto;

    // METHOD TO CHECK IF ORDER STATE HAS DELIVERED OR NO
    public boolean isOrderDelivered() {
        return stateOrder.equals(this.stateOrder);
    }

}
