package com.inventor.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.enums.StateOrder;
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

    @JsonIgnore
    private List<ProviderOrderLineDto> providerOrderLinesDto;

    public boolean isOrderDelivered() {
        return stateOrder.equals(this.stateOrder);
    }
}
