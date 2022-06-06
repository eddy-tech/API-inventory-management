package com.invertor.management.dto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CustomerOrderLineDto {

    private Long id;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private ArticleDto articleDto;

    private CustomerOrderDto customerOrderDto;

}
