package com.invertor.management.validators;

import com.invertor.management.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validate(CategoryDto categoryDto){
        List<String> errors = new ArrayList<>();

        if(categoryDto == null || !StringUtils.hasLength(categoryDto.getCodeCategory())) {
            errors.add("Can you please enter the category code ?");
        }
        return errors;
    }
}
