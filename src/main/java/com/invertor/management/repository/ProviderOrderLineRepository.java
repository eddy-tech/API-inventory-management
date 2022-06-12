package com.invertor.management.repository;

import com.invertor.management.entities.ProviderOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderOrderLineRepository extends JpaRepository<ProviderOrderLine, Long> {
}