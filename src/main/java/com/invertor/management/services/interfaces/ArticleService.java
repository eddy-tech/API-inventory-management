package com.invertor.management.services.interfaces;

import com.invertor.management.dto.ArticleDto;

import java.util.List;

public interface ArticleService {
    ArticleDto saveArticle (ArticleDto articleDto);
    ArticleDto findById (Long id);
    ArticleDto findByCodeArticle (String codeArticle);
    List<ArticleDto> listArticle ();
    void deleteArticle (Long id);
}
