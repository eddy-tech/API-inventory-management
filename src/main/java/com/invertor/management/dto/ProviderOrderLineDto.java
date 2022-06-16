package com.invertor.management.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProviderOrderLineDto {

    private Long id;

    private Long idEnterprise;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private ProviderOrderDto providerOrderDto;

    private ArticleDto articleDto;
}
