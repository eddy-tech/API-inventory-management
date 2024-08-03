package com.inventor.management.inventor_management.saleLine.repository;

import com.inventor.management.inventor_management.domains.Roles;
import com.inventor.management.inventor_management.saleLine.entity.SaleLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleLineRepository extends JpaRepository<SaleLine, Long> {
    List<SaleLine> findAllByArticleId (Long articleId);
    List<SaleLine> findAllBySaleId(Long orderId);

    interface RolesRepository extends JpaRepository<Roles, Long> {
        Roles findByRoleName(String roleName);
    }
}