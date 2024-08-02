package com.inventor.management.inventor_management.saleLine.dto;

import com.inventor.management.inventor_management.article.dto.ArticleDto;
import com.inventor.management.inventor_management.sale.dto.SaleDto;
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
