package com.invertor.management.validators;

import com.invertor.management.dto.SaleLineDto;

import java.util.ArrayList;
import java.util.List;

public class SaleLineValidator {
    public static List<String> validate (SaleLineDto saleLineDto) {
        List<String> errors = new ArrayList<>();

        if(saleLineDto == null) {
            errors.add("Can you enter at least a sale");
            errors.add("Can you enter at least an article");
            errors.add("Can you enter a quantity");
            errors.add("Can you enter a unit price");

            return errors;
        }
        if(saleLineDto.getSaleDto() == null){
            errors.add("Can you enter at least a sale");
        }
        if(saleLineDto.getArticleDto() == null){
            errors.add("Can you enter at least an article");
        }
        if(saleLineDto.getQuantity() == null){
            errors.add("Can you enter a quantity");
        }
        if(saleLineDto.getUnitPrice() == null){
            errors.add("Can you enter a unit price");
        }

        return errors;
    }
}
