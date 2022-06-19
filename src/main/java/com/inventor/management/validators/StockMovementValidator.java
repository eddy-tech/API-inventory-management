package com.inventor.management.validators;

import com.inventor.management.dto.StockMovementDto;

import java.util.ArrayList;
import java.util.List;

public class StockMovementValidator {
    public static List<String> validate (StockMovementDto stockMovementDto) {
        List<String> errors = new ArrayList<>();

        if(stockMovementDto == null){
            errors.add("Can you enter date movement");
            errors.add("Can you enter type of stock movement");
            errors.add("Can you enter an article");
            errors.add("Can you enter quantity");

            return errors;
        }
        if (stockMovementDto.getDateMovement() ==null){
            errors.add("Can you enter date movement");
        }
        if (stockMovementDto.getTypeMoveStock() ==null){
            errors.add("Can you enter type of stock movement");
        }
        if (stockMovementDto.getArticleDto() ==null){
            errors.add("Can you enter an article");
        }
        if (stockMovementDto.getQuantity() ==null){
            errors.add("Can you enter quantity");
        }

        return errors;
    }
}
