package com.inventor.management.saleLine.repository;

import com.inventor.management.core.entities.Roles;
import com.inventor.management.saleLine.entity.SaleLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleLineRepository extends JpaRepository<SaleLine, Long> {
    List<SaleLine> findAllByArticleId (Long articleId);
    List<SaleLine> findAllBySaleId(Long orderId);

    interface RolesRepository extends JpaRepository<Roles, Long> {
        Roles findByRoleName (String roleName);
    }
}