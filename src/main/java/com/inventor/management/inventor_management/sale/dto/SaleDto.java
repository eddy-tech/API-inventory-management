package com.inventor.management.inventor_management.sale.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.inventor_management.saleLine.entity.SaleLine;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class SaleDto {
    private Long id;
    private String codeSale;
    private Instant dateSale;
    private String comments;
    private Long id_enterprise;

    @JsonIgnore
    private List<SaleLine> saleLines;
}
