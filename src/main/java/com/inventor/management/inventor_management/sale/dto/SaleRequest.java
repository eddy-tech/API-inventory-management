package com.inventor.management.inventor_management.sale.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.inventor_management.saleLine.entity.SaleLine;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record SaleRequest(
        @NotNull(message = "date sale is required and must not be null")
        Instant dateSale,
        String comments,
        Long id_enterprise,
        @JsonIgnore
        List<SaleLine> saleLines
) {}
