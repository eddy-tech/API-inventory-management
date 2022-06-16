package com.invertor.management.dto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerOrderLineDto {

    private Long id;

    private Long idEnterprise;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private ArticleDto articleDto;

    private CustomerOrderDto customerOrderDto;

}
