package com.inventor.management.inventor_management.category.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private Long id;
    private String codeCategory;
    private String designation;
}
