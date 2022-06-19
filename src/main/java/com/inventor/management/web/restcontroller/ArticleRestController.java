package com.inventor.management.web.restcontroller;

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

    public ArticleDto saveArticle(ArticleDto articleDto) {
        return articleService.saveArticle(articleDto);
    }

    public ArticleDto updateArticle (ArticleDto articleDto){
        return articleService.updateArticle(articleDto);
    }

    public ArticleDto getArticle(Long id) {
        return articleService.getArticle(id);
    }

    public ArticleDto getCodeArticle(String codeArticle) {
        return articleService.getCodeArticle(codeArticle);
    }

    public List<ArticleDto> listArticle() {
        return articleService.listArticle();
    }

    public void deleteArticle(Long id) {
        articleService.deleteArticle(id);
    }
}
