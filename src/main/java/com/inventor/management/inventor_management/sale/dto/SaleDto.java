package com.inventor.management.inventor_management.sale.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import com.inventor.management.inventor_management.saleLine.entity.SaleLine;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class SaleDto {
    private Long id;
    private String codeSale;
    @NotNull(message = "date sale is required and must not be null")
    private Instant dateSale;
    private String comments;
    private Enterprise id_enterprise;

    @JsonIgnore
    private List<SaleLine> saleLines;
}
