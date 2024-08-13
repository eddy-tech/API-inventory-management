package com.inventor.management.inventor_management.customerOrderLine.dto;
import com.inventor.management.inventor_management.article.dto.ArticleDto;
import com.inventor.management.inventor_management.customerOrder.dto.CustomerOrderDto;
import com.inventor.management.inventor_management.customerOrder.entity.CustomerOrder;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CustomerOrderLineDto {
    private Long id;
    @NotNull(message = "quantity is required")
    private BigDecimal quantity;
    @NotNull(message = "unity price is required")
    private BigDecimal unitPrice;
    private ArticleDto articleDto;
    private CustomerOrderDto customerOrder;
}
