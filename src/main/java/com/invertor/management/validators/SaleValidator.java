package com.invertor.management.validators;

import com.invertor.management.dto.SaleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SaleValidator {

    public static List<String> validate (SaleDto saleDto){
        List<String> errors = new ArrayList<>();

        if(saleDto == null) {
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
