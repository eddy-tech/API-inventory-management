package com.inventor.management.inventor_management.providerOrderLine.repository;

import com.inventor.management.inventor_management.providerOrderLine.entity.ProviderOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderOrderLineRepository extends JpaRepository<ProviderOrderLine, Long> {
    List<ProviderOrderLine> findAllByProviderOrderId(Long orderId);
    List<ProviderOrderLine> findAllByArticleId(Long articleId);
}