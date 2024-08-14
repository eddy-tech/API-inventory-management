package com.inventor.management.inventor_management.saleLine.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SaleLineRequest {
    @NotNull(message = "Quantity must not be null")
    private BigDecimal quantity;
    @NotNull(message = "Unity price must not be null")
    private BigDecimal unitPrice;
    @NotNull(message = "Sale must not be null")
    private Long saleId;
    @NotNull(message = "Article must not be null")
    private Long articleId;
}
