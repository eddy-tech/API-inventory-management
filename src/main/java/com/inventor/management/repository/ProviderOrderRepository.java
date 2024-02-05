package com.inventor.management.repository;

import com.inventor.management.entities.ProviderOrder;
import com.inventor.management.entities.ProviderOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderOrderRepository extends JpaRepository<ProviderOrder, Long> {
    ProviderOrder findByCodeProviderOrder (String codeProviderOrder);
    List<ProviderOrder> findAllByProviderId (Long providerId);

}