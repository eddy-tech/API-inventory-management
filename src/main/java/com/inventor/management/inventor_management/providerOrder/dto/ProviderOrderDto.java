package com.inventor.management.inventor_management.providerOrder.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.inventor_management.providerOrderLine.dto.ProviderOrderLineDto;
import com.inventor.management.inventor_management.core.enums.StateOrder;
import com.inventor.management.inventor_management.provider.dto.ProviderDto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class ProviderOrderDto {
    private Long id;
    private String codeProviderOrder;
    @NotNull(message = "order date is required and must not be null")
    private Instant dateOrdering;
    @NotNull(message = "order state is required and must not be null")
    private StateOrder stateOrder;
    private ProviderDto providerDto;
    private Long id_enterprise;

    @JsonIgnore
    private List<ProviderOrderLineDto> providerOrderLinesDto;
    public boolean isOrderDelivered() {
        return StateOrder.DELIVERED.equals(this.stateOrder);
    }
}
