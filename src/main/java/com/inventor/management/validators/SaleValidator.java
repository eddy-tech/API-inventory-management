package com.inventor.management.validators;

import com.inventor.management.dto.SaleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SaleValidator {

    public static List<String> validate (SaleDto saleDto){
        List<String> errors = new ArrayList<>();

        if(saleDto == null) {
            errors.add("Can you enter your code sale");
            errors.add("Can you enter your date sale");

            return errors;
        }

        if(!StringUtils.hasLength(saleDto.getCodeSale())){
            errors.add("Can you enter your code sale");
        }

        if(saleDto.getDateSale() == null){
            errors.add("Can you enter your date sale");
        }

        return errors;
    }
}
