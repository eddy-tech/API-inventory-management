package com.inventor.management.inventor_management.customerOrderLine.dto;
import com.inventor.management.inventor_management.article.dto.ArticleDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerOrderLineDto {
    private Long id;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private ArticleDto articleDto;
}
