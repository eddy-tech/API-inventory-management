package com.inventor.management.web.restcontroller;

import com.inventor.management.dto.CustomerOrderLineDto;
import com.inventor.management.dto.ProviderOrderLineDto;
import com.inventor.management.dto.SaleLineDto;
import com.inventor.management.services.interfaces.ArticleService;
import com.inventor.management.dto.ArticleDto;
import com.inventor.management.web.api.ArticleApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ArticleRestController implements ArticleApi {

    private ArticleService articleService;

    @Override
    public ArticleDto saveArticle(ArticleDto articleDto) {
        return articleService.saveArticle(articleDto);
    }

    @Override
    public ArticleDto updateArticle(ArticleDto articleDto) {
        return articleService.updateArticle(articleDto);
    }

    @Override
    public ArticleDto getArticle(Long id) {
        return articleService.getArticle(id);
    }

    @Override
    public ArticleDto getCodeArticle(String codeArticle) {
        return articleService.getCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDto> listArticle() {
        return articleService.listArticle();
    }

    @Override
    public List<SaleLineDto> findHistorySales(Long articleId) {
        return articleService.findHistorySales(articleId);
    }

    @Override
    public List<CustomerOrderLineDto> findHistoryCustomerOrder(Long articleId) {
        return articleService.findHistoryCustomerOrder(articleId);
    }

    @Override
    public List<ProviderOrderLineDto> findHistoryProviderOrder(Long articleId) {
        return articleService.findHistoryProviderOrder(articleId);
    }

    @Override
    public List<ArticleDto> findAllArticleByCategory(Long categoryId) {
        return articleService.findAllArticleByCategory(categoryId);
    }

    @Override
    public void deleteArticle(Long id) {
         articleService.deleteArticle(id);
    }
}

