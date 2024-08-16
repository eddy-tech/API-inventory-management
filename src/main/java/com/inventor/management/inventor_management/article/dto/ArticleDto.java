package com.inventor.management.inventor_management.article.dto;

import com.inventor.management.inventor_management.category.dto.CategoryDto;
import com.inventor.management.inventor_management.enterprise.dto.EnterpriseDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ArticleDto {
    private String codeArticle;
    private Long id;
    private String designation;
    private BigDecimal unitPriceHt;
    private BigDecimal rateTax;
    private BigDecimal unitPriceTtc;
    private String picture;
    private CategoryDto categoryDto;
    private EnterpriseDto enterpriseDto;

}
