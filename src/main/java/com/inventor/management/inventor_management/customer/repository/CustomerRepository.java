package com.inventor.management.inventor_management.customer.repository;

import com.inventor.management.inventor_management.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}