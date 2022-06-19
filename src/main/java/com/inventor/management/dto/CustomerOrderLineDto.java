package com.inventor.management.dto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerOrderLineDto {

    private Long id;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private ArticleDto articleDto;

    private CustomerOrderDto customerOrderDto;

}
