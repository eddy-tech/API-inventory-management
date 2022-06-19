package com.inventor.management.repository;

import com.inventor.management.entities.ProviderOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderOrderRepository extends JpaRepository<ProviderOrder, Long> {

    public ProviderOrder findProviderOrderByCodeProviderOrder (String codeProviderOrder);
}