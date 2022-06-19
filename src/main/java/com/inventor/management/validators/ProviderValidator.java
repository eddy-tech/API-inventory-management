package com.inventor.management.validators;

import com.inventor.management.dto.ProviderDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProviderValidator {

    public static List<String> validate (ProviderDto providerDto) {
        List<String> errors = new ArrayList<>();

        if(providerDto == null) {
            errors.add("Can you enter name");
            errors.add("Can you enter surname");
            errors.add("Can you enter e-mail");
            errors.add("Can you enter phone number");

            return errors;
        }

        if(!StringUtils.hasLength(providerDto.getName())){
            errors.add("Can you enter name");
        }
        if(!StringUtils.hasLength(providerDto.getSurname())){
            errors.add("Can you enter surname");
        }
        if(!StringUtils.hasLength(providerDto.getMail())){
            errors.add("Can you enter e-mail");
        }
        if(!StringUtils.hasLength(providerDto.getNumTel())){
            errors.add("Can you enter phone number");
        }

        return errors;
    }

}
