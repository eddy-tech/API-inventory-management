package com.inventor.management.inventor_management.category.dto;

import com.inventor.management.inventor_management.enterprise.dto.EnterpriseDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {
    private Long id;
    private String codeCategory;
    private String designation;
    private EnterpriseDto id_enterprise;
}
