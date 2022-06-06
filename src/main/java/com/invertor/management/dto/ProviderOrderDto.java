package com.invertor.management.dto;

import com.invertor.management.entities.Provider;
import com.invertor.management.entities.ProviderOrderLine;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class ProviderOrderDto {

    private Long id;

    private String codeProviderOrder;

    private Instant dateOrdering;

    private ProviderDto providerDto;

    private List<ProviderOrderLineDto> providerOrderLinesDto;

}
