package com.invertor.management.web;

import com.invertor.management.dto.ArticleDto;
import com.invertor.management.services.impl.ArticleServiceImpl;
import com.invertor.management.services.interfaces.ArticleService;
import com.invertor.management.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.API_ROOT,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ArticleRestController {

    private ArticleService articleService;

    @PostMapping("/article")
    public ArticleDto saveArticle(@RequestBody ArticleDto articleDto) {
        return articleService.saveArticle(articleDto);
    }

    @PutMapping("/article")
    public ArticleDto updateArticle (@RequestBody ArticleDto articleDto){
        return articleService.updateArticle(articleDto);
    }

    @GetMapping("/article/{idArticle}")
    public ArticleDto findById(@PathVariable(name = "idArticle") Long id) {
        return articleService.findById(id);
    }

    @GetMapping("/article/{codeArticle}")
    public ArticleDto findByCodeArticle(@PathVariable(name = "codeArticle") String codeArticle) {
        return articleService.findByCodeArticle(codeArticle);
    }

    @GetMapping("/article")
    public List<ArticleDto> listArticle() {
        return articleService.listArticle();
    }

    @DeleteMapping("/article/{idArticle}")
    public void deleteArticle(@PathVariable(name = "idArticle") Long id) {
        articleService.deleteArticle(id);
    }
}
