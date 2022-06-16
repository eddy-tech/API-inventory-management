package com.invertor.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.invertor.management.entities.Provider;
import com.invertor.management.entities.ProviderOrderLine;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ProviderOrderDto {

    private Long id;

    private Long idEnterprise;

    private String codeProviderOrder;

    private Instant dateOrdering;

    private ProviderDto providerDto;

    private List<ProviderOrderLineDto> providerOrderLinesDto;

}
