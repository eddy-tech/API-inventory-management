package com.inventor.management.services.interfaces;

import com.inventor.management.dto.ArticleDto;

import java.util.List;

public interface ArticleService {
    ArticleDto saveArticle (ArticleDto articleDto);
    ArticleDto updateArticle (ArticleDto articleDto);
    ArticleDto getArticle (Long id);
    ArticleDto getCodeArticle (String codeArticle);
    List<ArticleDto> listArticle ();
    void deleteArticle (Long id);
}
