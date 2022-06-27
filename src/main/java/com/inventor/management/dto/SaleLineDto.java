package com.inventor.management.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleLineDto {

    private Long id;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private Long id_enterprise;

    private SaleDto saleDto;

    private ArticleDto articleDto;
}
