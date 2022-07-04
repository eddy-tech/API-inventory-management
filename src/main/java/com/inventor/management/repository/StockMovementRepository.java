package com.inventor.management.repository;

import com.inventor.management.entities.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {

    @Query("select sum(m.quantity) from StockMovement m where m.article.id = :idArticle")
    BigDecimal stockRealArticle(@Param("idArticle") Long articleId);
    List<StockMovement> findAllByArticleId (Long articleId);
}