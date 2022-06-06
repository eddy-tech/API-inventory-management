package com.invertor.management.validators;

import com.invertor.management.dto.UserDto;
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
            errors.add("Can you please enter your address");

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
        if(userDto.getAddressDto() == null) {
            errors.add("Can you please enter your address");
        } else {
            if(!StringUtils.hasLength(userDto.getAddressDto().getAddress1())) {
                errors.add("Field 'Address 1' is required");
            }
            if(!StringUtils.hasLength(userDto.getAddressDto().getCity())) {
                errors.add("Field 'City' is required");
            }
            if(!StringUtils.hasLength(userDto.getAddressDto().getCountry())) {
                errors.add("Field 'Country' is required");
            }
            if(!StringUtils.hasLength(userDto.getAddressDto().getCodePostal())) {
                errors.add("Field 'Code Postal' is required");
            }
        }
        if(!StringUtils.hasLength(userDto.getNameUser())) {
            errors.add("Can you please enter your username");
        }
        if(!StringUtils.hasLength(userDto.getNameUser())) {
            errors.add("Can you please enter your username");
        }
        return errors;
    }
}
