package com.inventor.management.inventor_management.article.dto;

import java.math.BigDecimal;

public record ArticleRequest(
        String designation, BigDecimal unitPriceHt, BigDecimal rateTax, BigDecimal unitPriceTtc, String picture
) {}
