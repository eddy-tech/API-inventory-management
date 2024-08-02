package com.inventor.management.inventor_management.category.repository;


import com.inventor.management.inventor_management.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCodeCategory(String codeCategory);



}