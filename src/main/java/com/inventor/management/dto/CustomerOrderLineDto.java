package com.inventor.management.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerOrderLineDto {

    private Long id;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private ArticleDto articleDto;

    private Long id_enterprise;

    @JsonIgnore
    private CustomerOrderDto customerOrderDto;

}
