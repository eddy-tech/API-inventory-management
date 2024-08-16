package com.inventor.management.inventor_management.sale.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import com.inventor.management.inventor_management.saleLine.dto.SaleLineDto;
import com.inventor.management.inventor_management.saleLine.entity.SaleLine;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class SaleDto {
    private Long id;
    private String codeSale;
    private Instant dateSale;
    private String comments;
    @JsonIgnore
    private List<SaleLineDto> saleLines;
}
