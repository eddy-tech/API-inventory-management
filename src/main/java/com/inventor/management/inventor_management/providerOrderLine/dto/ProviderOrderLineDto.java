package com.inventor.management.inventor_management.providerOrderLine.dto;

import com.inventor.management.inventor_management.article.dto.ArticleDto;
import com.inventor.management.inventor_management.providerOrder.dto.ProviderOrderDto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProviderOrderLineDto {
    private Long id;
    @NotNull(message = "quantity is required")
    private BigDecimal quantity;
    @NotNull(message = "unity price is required")
    private BigDecimal unitPrice;
    private ProviderOrderDto providerOrderDto;
    private ArticleDto articleDto;
}
