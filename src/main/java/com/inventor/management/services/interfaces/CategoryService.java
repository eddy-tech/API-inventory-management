package com.inventor.management.services.interfaces;

import com.inventor.management.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto saveCategory (CategoryDto categoryDto);

    CategoryDto updateCategory (CategoryDto categoryDto);

    CategoryDto getCategory (Long id);

    CategoryDto getCodeCategory (String codeCategory);

    List<CategoryDto> listCategory ();

    void deleteCategory (Long id);
}

