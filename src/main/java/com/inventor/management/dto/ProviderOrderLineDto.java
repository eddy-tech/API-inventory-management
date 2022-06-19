package com.inventor.management.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProviderOrderLineDto {

    private Long id;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private ProviderOrderDto providerOrderDto;

    private ArticleDto articleDto;
}
