package com.inventor.management.inventor_management.category.mapper;

import com.inventor.management.inventor_management.category.dto.CategoryDto;
import com.inventor.management.inventor_management.category.dto.CategoryRequest;
import com.inventor.management.inventor_management.category.entity.Category;
import com.inventor.management.inventor_management.enterprise.mapper.EnterpriseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CategoryMapper {
    private final EnterpriseMapper enterpriseMapper;
    public Category fromCategory (CategoryRequest categoryRequest){
        return Category.builder()
                .designation(categoryRequest.designation())
                .creationTime(Instant.now())
                .build();
    }

    public CategoryDto fromCategoryDto (Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .codeCategory(category.getCodeCategory())
                .designation(category.getDesignation())
                .codeCategory(category.getCodeCategory())
                .id_enterprise(enterpriseMapper.fromEnterprise(category.getEnterprise()))
                .build();
    }
}
