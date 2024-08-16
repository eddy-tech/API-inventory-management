package com.inventor.management.inventor_management.providerOrder.repository;

import com.inventor.management.inventor_management.providerOrder.entity.ProviderOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderOrderRepository extends JpaRepository<ProviderOrder, Long> {
    ProviderOrder findByCodeProviderOrder (String codeProviderOrder);
    List<ProviderOrder> findAllByProviderId (Long providerId);

}