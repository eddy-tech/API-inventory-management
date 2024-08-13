package com.inventor.management.inventor_management.article.service;

import com.inventor.management.inventor_management.article.dto.ArticleDto;
import com.inventor.management.inventor_management.article.dto.ArticleRequest;
import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.customerOrderLine.dto.CustomerOrderLineDto;
import com.inventor.management.inventor_management.providerOrderLine.dto.ProviderOrderLineDto;
import com.inventor.management.inventor_management.saleLine.dto.SaleLineDto;

import java.util.List;

public interface ArticleService {
    ArticleDto saveArticle (ArticleRequest request);
    ArticleDto updateArticle (ArticleRequest articleDto, Long id);
    ArticleDto getArticle (Long id);
    ArticleDto getCodeArticle (String codeArticle);
    List<ArticleDto> listArticle ();
    List<SaleLineDto> findHistorySales (Long articleId);
    List<CustomerOrderLineDto> findHistoryCustomerOrder (Long articleId);
    List<ProviderOrderLineDto> findHistoryProviderOrder (Long articleId);
    List<ArticleDto> findAllArticleByCategory (Long categoryId);
    Article findById (Long id);
    void deleteArticle (Long id);
}
