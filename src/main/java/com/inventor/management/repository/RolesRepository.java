package com.inventor.management.repository;

import com.inventor.management.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {

    Roles findByRoleName (String roleName);
}