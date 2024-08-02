package com.inventor.management.customerOrderLine.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.article.dto.ArticleDto;
import com.inventor.management.customerOrder.dto.CustomerOrderDto;
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
