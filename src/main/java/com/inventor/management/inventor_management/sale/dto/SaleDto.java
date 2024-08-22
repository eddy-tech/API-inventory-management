package com.inventor.management.inventor_management.sale.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.inventor_management.saleLine.dto.SaleLineDto;
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
