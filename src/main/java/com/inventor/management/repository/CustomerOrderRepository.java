package com.inventor.management.repository;

import com.inventor.management.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    public CustomerOrder findCustomerOrderByCodeOrderCustomer (String codeOrderCustomer);
}