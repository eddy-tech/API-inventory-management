package com.inventor.management.enterprise.repository;

import com.inventor.management.enterprise.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}