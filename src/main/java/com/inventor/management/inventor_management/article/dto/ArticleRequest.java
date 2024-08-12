package com.inventor.management.inventor_management.article.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ArticleRequest(
        @NotNull(message = "Designation is required")
        String designation,
        @NotNull(message = "Unity price out tax is required")
        BigDecimal unitPriceHt,
        @NotNull(message = "rate tax is required")
        BigDecimal rateTax,
        @NotNull(message = "Unity price all taxes include is required")
        BigDecimal unitPriceTtc,
        @NotNull(message = "You have to provide at leas one picture")
        String picture,
        @NotNull(message = "You have to provide a category of article")
        String codeCategory
) {}
