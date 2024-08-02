package com.inventor.management.category.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.article.dto.ArticleDto;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {

    private Long id;

    private String codeCategory;

    private String designation;

    private Long id_enterprise;

    @JsonIgnore
    private List<ArticleDto> articlesDto;

}
