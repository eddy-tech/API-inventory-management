package com.inventor.management.validators;

import com.inventor.management.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validate(CategoryDto categoryDto) {
        List<String> errors = new ArrayList<>();

        if (categoryDto == null || !StringUtils.hasLength(categoryDto.getCodeCategory())) {
            errors.add("Can you please enter the category code");
            errors.add("Can you please enter your designation");
            return errors;
        }

        if (!StringUtils.hasLength(categoryDto.getCodeCategory())) {
            errors.add("Can you please enter the category code");
        }
        if(!StringUtils.hasLength(categoryDto.getDesignation())){
            errors.add("Can you please enter your designation");
        }

        return errors;
    }
}
