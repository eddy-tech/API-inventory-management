package com.inventor.management.inventor_management.customerOrder.dto;

import com.inventor.management.inventor_management.customer.dto.CustomerDto;
import com.inventor.management.inventor_management.core.enums.StateOrder;
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

    public boolean isOrderDelivered() {
        return StateOrder.DELIVERED.equals(this.stateOrder);
    }

}
