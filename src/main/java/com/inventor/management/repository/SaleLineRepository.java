package com.inventor.management.repository;

import com.inventor.management.entities.SaleLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleLineRepository extends JpaRepository<SaleLine, Long> {

    List<SaleLine> findAllByArticleId (Long articleId);
    List<SaleLine> findAllBySaleId(Long orderId);
}