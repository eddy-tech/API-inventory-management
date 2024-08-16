package com.inventor.management.inventor_management.sale.dto;

import com.inventor.management.inventor_management.saleLine.dto.SaleLineRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record SaleRequest(
        @NotNull(message = "date sale is required and must not be null")
        Instant dateSale,
        String comments,
        @NotNull(message = "Sale lines must not be null or empty")
        List<SaleLineRequest> saleLines
) {}
