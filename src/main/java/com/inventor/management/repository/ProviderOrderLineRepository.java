package com.inventor.management.repository;

import com.inventor.management.entities.ProviderOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderOrderLineRepository extends JpaRepository<ProviderOrderLine, Long> {
    List<ProviderOrderLine> findAllByProviderOrderId(Long orderId);
    List<ProviderOrderLine> findAllByArticleId(Long articleId);
}