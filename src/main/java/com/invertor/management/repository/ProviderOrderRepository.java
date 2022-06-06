package com.invertor.management.repository;

import com.invertor.management.entities.ProviderOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderOrderRepository extends JpaRepository<ProviderOrder, Long> {
}