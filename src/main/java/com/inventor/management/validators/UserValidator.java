package com.inventor.management.validators;

import com.inventor.management.dto.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public static List<String> validate (UserDto userDto){
        List<String> errors = new ArrayList<>();

        if(userDto ==null) {
            errors.add("Can you please enter your username");
            errors.add("Can you please enter your surname");
            errors.add("Can you please enter user birthday");
            errors.add("Can you please enter your password");
            errors.addAll(AddressValidator.validate(null));

            return errors;
        }

        if(!StringUtils.hasLength(userDto.getNameUser())) {
            errors.add("Can you please enter your username");
        }
        if(!StringUtils.hasLength(userDto.getSurnameUser())) {
            errors.add("Can you please enter your surname");
        }
        if(!StringUtils.hasLength(userDto.getMail())) {
            errors.add("Can you please enter your e-mail");
        }
        if(userDto.getBirthDate() == null) {
            errors.add("Can you please enter user birthday");
        }
        if(!StringUtils.hasLength(userDto.getPassword())) {
            errors.add("Can you please enter your password");
        }

        errors.addAll(AddressValidator.validate(userDto.getAddressDto()));
        return errors;
    }
}
