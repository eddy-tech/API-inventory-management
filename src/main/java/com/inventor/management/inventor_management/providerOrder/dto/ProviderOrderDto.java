package com.inventor.management.inventor_management.providerOrder.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.inventor_management.providerOrderLine.dto.ProviderOrderLineDto;
import com.inventor.management.core.enums.StateOrder;
import com.inventor.management.inventor_management.provider.dto.ProviderDto;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ProviderOrderDto {
    private Long id;
    private String codeProviderOrder;
    private Instant dateOrdering;
    private StateOrder stateOrder;
    private ProviderDto providerDto;
    private Long id_enterprise;

    @JsonIgnore
    private List<ProviderOrderLineDto> providerOrderLinesDto;

    public boolean isOrderDelivered() {
        return stateOrder.equals(this.stateOrder);
    }
}
