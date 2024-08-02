package com.inventor.management.inventor_management.category.service;

import com.inventor.management.inventor_management.category.dto.CategoryDto;
import com.inventor.management.inventor_management.category.dto.CategoryRequest;

import java.util.List;

public interface CategoryService {
    CategoryDto saveCategory (CategoryRequest categoryRequest);
    CategoryDto updateCategory (CategoryRequest categoryRequest, Long id);
    CategoryDto getCategory (Long id);
    CategoryDto getCodeCategory (String codeCategory);
    List<CategoryDto> listCategory ();
    void deleteCategory (Long id);
}

