package com.inventor.management.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ProviderOrderDto {

    private Long id;

    private String codeProviderOrder;

    private Instant dateOrdering;

    private ProviderDto providerDto;

    private List<ProviderOrderLineDto> providerOrderLinesDto;

}
