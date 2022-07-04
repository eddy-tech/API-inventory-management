package com.inventor.management.services.interfaces;

import com.inventor.management.dto.ArticleDto;
import com.inventor.management.dto.CustomerOrderLineDto;
import com.inventor.management.dto.ProviderOrderLineDto;
import com.inventor.management.dto.SaleLineDto;
import com.inventor.management.entities.SaleLine;

import java.util.List;

public interface ArticleService {
    ArticleDto saveArticle (ArticleDto articleDto);
    ArticleDto updateArticle (ArticleDto articleDto);
    ArticleDto getArticle (Long id);
    ArticleDto getCodeArticle (String codeArticle);
    List<ArticleDto> listArticle ();
    List<SaleLineDto> findHistorySales (Long articleId);
    List<CustomerOrderLineDto> findHistoryCustomerOrder (Long articleId);
    List<ProviderOrderLineDto> findHistoryProviderOrder (Long articleId);
    List<ArticleDto> findAllArticleByCategory (Long categoryId);
    void deleteArticle (Long id);
}
