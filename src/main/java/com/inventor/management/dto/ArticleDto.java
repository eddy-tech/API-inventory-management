package com.inventor.management.dto;

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

    /*
    public static ArticleDto fromArticle (Article article){
        if(article == null){
            return null;
        }
        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .unitPriceHt(article.getUnitPriceHt())
                .rateTax(article.getRateTax())
                .unitPriceTtc(article.getUnitPriceTtc())
                .picture(article.getPicture())
                .build();
    }

    public static Article fromArticleDto (ArticleDto articleDto){
        if(articleDto == null) return null;
        Article article = new Article();
        article.setId(articleDto.getId());
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setUnitPriceHt(articleDto.getUnitPriceHt());
        article.setRateTax(articleDto.getRateTax());
        article.setUnitPriceTtc(articleDto.getUnitPriceTtc());
        article.setPicture(articleDto.getPicture());

        return article;
    }
    */
}
