package com.inventor.management.validators;

import com.inventor.management.dto.StockMovementDto;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
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
        if (!StringUtils.hasLength(stockMovementDto.getTypeMoveStock().name())){
            // RENVOIE name() EN TANT QUE CHAINE DE CARACTERE
            errors.add("Can you enter type of stock movement");
        }
        if (stockMovementDto.getArticleDto() ==null || stockMovementDto.getArticleDto().getId() == null){
            errors.add("Can you enter an article");
        }
        // compareTo () => renvoie -1 si inférieur, 0 si égale et 1 si supérieur
        if (stockMovementDto.getQuantity() ==null || stockMovementDto.getQuantity().compareTo(BigDecimal.ZERO) == 0){
           errors.add("Can you enter movement quantity");
        }

        return errors;
    }
}
