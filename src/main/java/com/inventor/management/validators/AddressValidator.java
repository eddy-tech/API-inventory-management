package com.inventor.management.validators;

import com.inventor.management.dto.AddressDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AddressValidator {

    public  static List<String> validate(AddressDto addressDto){
        List<String> errors = new ArrayList<>();

        if(addressDto == null){
            errors.add("Please enter address 1");
            errors.add("Please enter city");
            errors.add("Please enter country");
            errors.add("Please enter code postal");

            return errors;
        }
        if(!StringUtils.hasLength(addressDto.getAddress1())){
            errors.add("Please enter address 1");
        }
        if(!StringUtils.hasLength(addressDto.getCity())){
            errors.add("Please enter city");
        }
        if(!StringUtils.hasLength(addressDto.getCountry())){
            errors.add("Please enter country");
        }
        if(!StringUtils.hasLength(addressDto.getCodePostal())){
            errors.add("Please enter code postal");
        }
        return errors;
    }
}
