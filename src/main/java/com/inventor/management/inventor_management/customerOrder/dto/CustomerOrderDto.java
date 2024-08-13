package com.inventor.management.inventor_management.customerOrder.dto;

import com.inventor.management.inventor_management.customer.dto.CustomerDto;
import com.inventor.management.inventor_management.core.enums.StateOrder;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class CustomerOrderDto {
    private Long id;
    private String codeOrderCustomer;
    private Instant dateOrder;
    private StateOrder stateOrder;
    private CustomerDto customerDto;

    private Long id_enterprise;

    // METHOD TO CHECK IF ORDER STATE HAS BEEN DELIVERED OR NO
    public boolean isOrderDelivered() {
        return stateOrder.equals(this.stateOrder);
    }

}
