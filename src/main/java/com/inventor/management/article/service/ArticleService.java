package com.inventor.management.article.service;

import com.inventor.management.article.dto.ArticleDto;
import com.inventor.management.customerOrderLine.dto.CustomerOrderLineDto;
import com.inventor.management.providerOrderLine.dto.ProviderOrderLineDto;
import com.inventor.management.saleLine.dto.SaleLineDto;

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
