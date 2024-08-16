package com.inventor.management.inventor_management.enterprise.repository;

import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}