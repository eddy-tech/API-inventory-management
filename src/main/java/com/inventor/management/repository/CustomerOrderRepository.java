package com.inventor.management.repository;

import com.inventor.management.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    CustomerOrder findCustomerOrderByCodeOrderCustomer (String codeOrderCustomer);

    List<CustomerOrder> findAllByCustomerId (Long customerId);
}