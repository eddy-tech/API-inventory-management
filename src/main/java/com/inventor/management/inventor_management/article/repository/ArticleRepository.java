package com.inventor.management.inventor_management.article.repository;

import com.inventor.management.inventor_management.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findByCodeArticle(String codeArticle);
    List<Article> findAllByCategoryId (Long categoryId);

}