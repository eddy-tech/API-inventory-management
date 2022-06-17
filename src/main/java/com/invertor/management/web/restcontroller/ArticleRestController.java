package com.invertor.management.web.restcontroller;

import com.invertor.management.dto.ArticleDto;
import com.invertor.management.services.impl.ArticleServiceImpl;
import com.invertor.management.services.interfaces.ArticleService;
import com.invertor.management.utils.Constants;
import com.invertor.management.web.api.ArticleApi;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
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
