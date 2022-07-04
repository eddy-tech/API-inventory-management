package com.inventor.management.repository;

import com.inventor.management.dto.CustomerOrderLineDto;
import com.inventor.management.entities.CustomerOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerOrderLineRepository extends JpaRepository<CustomerOrderLine, Long> {
    List<CustomerOrderLine> findAllByCustomerOrderId(Long id);
    List<CustomerOrderLine> findAllByArticleId(Long articleId);
}