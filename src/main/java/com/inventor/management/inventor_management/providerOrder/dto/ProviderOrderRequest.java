package com.inventor.management.inventor_management.providerOrder.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.inventor_management.core.enums.StateOrder;
import com.inventor.management.inventor_management.providerOrderLine.dto.ProviderOrderLineDto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ProviderOrderRequest(
        @NotNull(message = "order date is required and must not be null")
        Instant dateOrder,
        @NotNull(message = "order state is required and must not be null")
        StateOrder stateOrder,
        Long providerId,
        @JsonIgnore
        List<ProviderOrderLineDto> providerOrderLineDto
) {
    public boolean isOrderDelivered() {
        return StateOrder.DELIVERED.equals(this.stateOrder);
    }
}
