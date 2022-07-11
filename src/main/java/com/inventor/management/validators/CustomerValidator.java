package com.inventor.management.validators;

import com.inventor.management.dto.CustomerDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerValidator {

    public static List<String> validate (CustomerDto customerDto) {
        List<String> errors = new ArrayList<>();

        if(customerDto == null) {
            errors.add("Can you enter your name");
            errors.add("Can you enter your surname");
            errors.add("Can you enter your e-mail");
            errors.add("Can you enter your phone number");
            errors.addAll(AddressValidator.validate(null));

            return errors;
        }

        if(!StringUtils.hasLength(customerDto.getName())) {
            errors.add("Can you enter your name");
        }
        if(!StringUtils.hasLength(customerDto.getSurname())) {
            errors.add("Can you enter your surname");
        }
        if(!StringUtils.hasLength(customerDto.getMail())) {
            errors.add("Can you enter your e-mail");
        }
        if(!StringUtils.hasLength(customerDto.getNumTel())) {
            errors.add("Can you enter your phone number");
        }

        errors.addAll(AddressValidator.validate(customerDto.getAddressDto()));
        return errors;
    }
}
