package com.inventor.management.inventor_management.user.repository;

import com.inventor.management.inventor_management.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMail (String email);
}