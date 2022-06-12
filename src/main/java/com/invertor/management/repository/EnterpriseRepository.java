package com.invertor.management.repository;

import com.invertor.management.entities.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}