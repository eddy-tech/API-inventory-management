package com.invertor.management.repository;

import com.invertor.management.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findArticleByCodeArticle (String codeArticle);

}