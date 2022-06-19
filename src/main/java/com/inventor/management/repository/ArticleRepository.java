package com.inventor.management.repository;

import com.inventor.management.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findArticleByCodeArticle (String codeArticle);

}