package com.inventor.management.inventor_management.article.mapper;

import com.inventor.management.inventor_management.article.dto.ArticleDto;
import com.inventor.management.inventor_management.article.dto.ArticleRequest;
import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.category.entity.Category;
import com.inventor.management.inventor_management.category.mapper.CategoryMapper;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import com.inventor.management.inventor_management.enterprise.mapper.EnterpriseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ArticleMapper {
    private final CategoryMapper categoryMapper;
    private final EnterpriseMapper enterpriseMapper;
    public Article fromArticle (ArticleRequest articleRequest, Category category, Enterprise enterprise){
        return Article.builder()
                .designation(articleRequest.designation())
                .rateTax(articleRequest.rateTax())
                .unitPriceHt(articleRequest.unitPriceHt())
                .unitPriceTtc(articleRequest.unitPriceTtc())
                .picture(articleRequest.picture())
                .category(category)
                .enterprise(enterprise)
                .creationTime(Instant.now())
                .build();
    }

    public ArticleDto fromArticleDto (Article article){
        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .rateTax(article.getRateTax())
                .unitPriceHt(article.getUnitPriceHt())
                .unitPriceTtc(article.getUnitPriceTtc())
                .picture(article.getPicture())
                .categoryDto(categoryMapper.fromCategoryDto(article.getCategory()))
                .enterpriseDto(enterpriseMapper.fromEnterprise(article.getEnterprise()))
                .build();
    }
}
