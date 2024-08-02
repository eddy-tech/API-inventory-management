package com.inventor.management.inventor_management.customerOrder.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.core.enums.StateOrder;
import com.inventor.management.inventor_management.customerOrderLine.dto.CustomerOrderLineDto;

import java.time.Instant;
import java.util.List;

public record CustomerOrderRequest(
        Instant dateOrder, StateOrder stateOrder, Long customerId,
        @JsonIgnore
        List<CustomerOrderLineDto> customerOrderLinesDto
) {
        // METHOD TO CHECK IF ORDER STATE HAS BEEN DELIVERED OR NO
        public boolean isOrderDelivered() {
                return stateOrder.equals(this.stateOrder);
        }
}
