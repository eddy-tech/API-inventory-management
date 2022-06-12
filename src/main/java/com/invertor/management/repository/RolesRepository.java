package com.invertor.management.repository;

import com.invertor.management.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
}