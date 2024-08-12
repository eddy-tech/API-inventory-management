package com.inventor.management.inventor_management.providerOrderLine.dto;

import com.inventor.management.inventor_management.article.dto.ArticleDto;
import com.inventor.management.inventor_management.providerOrder.dto.ProviderOrderDto;
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
