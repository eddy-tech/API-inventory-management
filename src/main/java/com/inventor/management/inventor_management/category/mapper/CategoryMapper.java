package com.inventor.management.inventor_management.category.mapper;

import com.inventor.management.inventor_management.category.dto.CategoryDto;
import com.inventor.management.inventor_management.category.dto.CategoryRequest;
import com.inventor.management.inventor_management.category.entity.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    public Category fromCategory (CategoryRequest categoryRequest){
        Category category = new Category();
        BeanUtils.copyProperties(categoryRequest,category);
        return category;
    }

    public CategoryDto fromCategoryDto (Category category){
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(categoryDto,category);
        return categoryDto;
    }
}
