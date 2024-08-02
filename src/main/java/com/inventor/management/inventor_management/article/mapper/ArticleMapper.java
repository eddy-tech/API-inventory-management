package com.inventor.management.inventor_management.article.mapper;

import com.inventor.management.inventor_management.article.dto.ArticleDto;
import com.inventor.management.inventor_management.article.dto.ArticleRequest;
import com.inventor.management.inventor_management.article.entity.Article;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ArticleMapper {
    public Article fromArticle (ArticleRequest articleRequest){
        Article article = new Article();
        BeanUtils.copyProperties(articleRequest, article);
        return article;
    }

    public ArticleDto fromArticleDto (Article article){
        ArticleDto articleDto = new ArticleDto();
        BeanUtils.copyProperties(articleDto,article);
        return articleDto;
    }
}
