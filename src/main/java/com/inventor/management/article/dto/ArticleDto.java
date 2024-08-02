package com.inventor.management.article.dto;

import com.inventor.management.category.dto.CategoryDto;
import com.inventor.management.enterprise.dto.EnterpriseDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
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
