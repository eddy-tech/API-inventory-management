package com.invertor.management.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SaleLineDto {

    private Long id;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private SaleDto saleDto;

    private ArticleDto articleDto;
}
