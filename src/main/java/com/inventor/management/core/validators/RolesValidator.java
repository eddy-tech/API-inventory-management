package com.inventor.management.core.validators;

import com.inventor.management.core.dto.RolesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RolesValidator {

    public static List<String> validate (RolesDto rolesDto) {
        List<String> errors = new ArrayList<>();

        if(!StringUtils.hasLength(rolesDto.getRoleName())) {
            errors.add("Can you enter you role name");
        }
        if(rolesDto.getUserDto() == null) {
            errors.add("Can you enter users you have roles");
        }
        return errors;
    }
}
