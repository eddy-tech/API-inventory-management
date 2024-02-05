package com.inventor.management.mapper;

import com.inventor.management.dto.ArticleDto;
import com.inventor.management.dto.CategoryDto;
import com.inventor.management.entities.Article;
import com.inventor.management.entities.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    public CategoryDto fromCategory (Category category){
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category,categoryDto);
        return categoryDto;
    }

    public Category fromCategoryDto (CategoryDto categoryDto){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto,category);
        return category;
    }
}
