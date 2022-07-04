package com.inventor.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
