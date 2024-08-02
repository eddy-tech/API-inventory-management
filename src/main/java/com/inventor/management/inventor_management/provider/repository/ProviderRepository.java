package com.inventor.management.inventor_management.provider.repository;

import com.inventor.management.inventor_management.provider.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}