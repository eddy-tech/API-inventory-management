package com.inventor.management.article.mapper;

import com.inventor.management.article.dto.ArticleDto;
import com.inventor.management.article.entity.Article;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ArticleMapper {
    public ArticleDto fromArticle (Article article){
        ArticleDto articleDto = new ArticleDto();
        BeanUtils.copyProperties(article,articleDto);
        return articleDto;
    }

    public Article fromArticleDto (ArticleDto articleDto){
        Article article = new Article();
        BeanUtils.copyProperties(articleDto,article);
        return article;
    }
}
