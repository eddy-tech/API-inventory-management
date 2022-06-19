package com.inventor.management.repository;

import com.inventor.management.entities.ProviderOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderOrderLineRepository extends JpaRepository<ProviderOrderLine, Long> {
}