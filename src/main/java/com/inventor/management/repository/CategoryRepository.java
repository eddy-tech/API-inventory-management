package com.inventor.management.repository;

import com.inventor.management.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByCodeCategory(String codeCategory);



}