package com.inventor.management.providerOrderLine.dto;

import com.inventor.management.article.dto.ArticleDto;
import com.inventor.management.providerOrder.dto.ProviderOrderDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProviderOrderLineDto {

    private Long id;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private Long id_enterprise;

    private ProviderOrderDto providerOrderDto;

    private ArticleDto articleDto;
}
