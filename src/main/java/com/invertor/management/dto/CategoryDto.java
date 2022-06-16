package com.invertor.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.invertor.management.entities.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {

    private Long id;

    private Long idEnterprise;

    private String codeCategory;

    private String designation;

    @JsonIgnore
    private List<ArticleDto> articlesDto;

    /*
    public CategoryDto fromCategory (Category category){
        if(category == null) {
            return null;
            // TODO throw an exception
        }
        return CategoryDto.builder()
                .id(category.getId())
                .codeCategory(category.getCodeCategory())
                .designation(category.getDesignation())
                .build();
    }

    public Category fromCategoryDto (CategoryDto categoryDto){
        if(categoryDto == null){
            return null;
        }
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCodeCategory(categoryDto.getCodeCategory());
        category.setDesignation(categoryDto.getDesignation());

        return category;
    }
*/
}
