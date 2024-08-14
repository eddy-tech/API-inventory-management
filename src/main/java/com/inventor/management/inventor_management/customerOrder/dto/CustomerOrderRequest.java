package com.inventor.management.inventor_management.customerOrder.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.inventor_management.core.enums.StateOrder;
import com.inventor.management.inventor_management.customerOrderLine.dto.CustomerOrderLineDto;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;

public record CustomerOrderRequest(
        @NotNull(message = "order date is required and must not be null")
        Instant dateOrder,
        @NotNull(message = "order state is required")
        StateOrder stateOrder,
        Long customerId,
        @JsonIgnore
        List<CustomerOrderLineDto> customerOrderLinesDto
) {
        // METHOD TO CHECK IF ORDER STATE HAS BEEN DELIVERED OR NO
        public boolean isOrderDelivered() {
                return StateOrder.DELIVERED.equals(this.stateOrder);
        }
}
