package com.inventor.management.repository;

import com.inventor.management.entities.CustomerOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderLineRepository extends JpaRepository<CustomerOrderLine, Long> {
}