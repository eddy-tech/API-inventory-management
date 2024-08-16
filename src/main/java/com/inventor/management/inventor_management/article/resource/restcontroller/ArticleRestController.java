package com.inventor.management.inventor_management.article.resource.restcontroller;

import com.inventor.management.inventor_management.article.dto.ArticleRequest;
import com.inventor.management.inventor_management.customerOrderLine.dto.CustomerOrderLineDto;
import com.inventor.management.inventor_management.article.service.ArticleService;
import com.inventor.management.inventor_management.providerOrderLine.dto.ProviderOrderLineDto;
import com.inventor.management.inventor_management.saleLine.dto.SaleLineDto;
import com.inventor.management.inventor_management.article.dto.ArticleDto;
import com.inventor.management.inventor_management.article.resource.api.ArticleApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.inventor.management.inventor_management.article.roots.ArticleEndPoint.ARTICLE_ENDPOINT;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(ARTICLE_ENDPOINT)
public class ArticleRestController implements ArticleApi {
    private final ArticleService articleService;

    @Override
    public ResponseEntity<ArticleDto> saveArticle(ArticleRequest articleRequest) {
        return ResponseEntity.status(CREATED)
                .body(articleService.saveArticle(articleRequest));
    }

    @Override
    public ResponseEntity<ArticleDto> updateArticle(ArticleRequest articleRequest, Long id) {
        return ResponseEntity.ok(articleService.updateArticle(articleRequest, id));
    }

    @Override
    public ResponseEntity<ArticleDto> getArticle(Long id) {
        return ResponseEntity.ok(articleService.getArticle(id));
    }

    @Override
    public ResponseEntity<ArticleDto> getCodeArticle(String codeArticle) {
        return ResponseEntity.ok(articleService.getCodeArticle(codeArticle));
    }

    @Override
    public ResponseEntity<List<ArticleDto> >listArticle() {
        return ResponseEntity.ok(articleService.listArticle());
    }

    @Override
    public ResponseEntity<List<SaleLineDto>> findHistorySales(Long articleId) {
        return ResponseEntity.ok(articleService.findHistorySales(articleId));
    }

    @Override
    public ResponseEntity<List<CustomerOrderLineDto>> findHistoryCustomerOrder(Long articleId) {
        return ResponseEntity.ok(articleService.findHistoryCustomerOrder(articleId));
    }

    @Override
    public ResponseEntity<List<ProviderOrderLineDto>> findHistoryProviderOrder(Long articleId) {
        return ResponseEntity.ok(articleService.findHistoryProviderOrder(articleId));
    }

    @Override
    public ResponseEntity<List<ArticleDto>> findAllArticleByCategory(Long categoryId) {
        return ResponseEntity.ok(articleService.findAllArticleByCategory(categoryId));
    }

    @Override
    public ResponseEntity<?> deleteArticle(Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}

