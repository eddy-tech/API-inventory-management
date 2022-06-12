package com.invertor.management.validators;

import com.invertor.management.dto.ProviderOrderLineDto;

import java.util.ArrayList;
import java.util.List;

public class ProviderOrderLineValidator {
    public static List<String> validate (ProviderOrderLineDto providerOrderLineDto){
        List<String> errors = new ArrayList<>();

        if(providerOrderLineDto == null){
            errors.add("Can you enter at least a customer order");
            errors.add("Can you enter at least a article");
            errors.add("Can you enter a quantity of order");
            errors.add("Can you enter your unit price");

            return errors;
        }

        if(providerOrderLineDto.getProviderOrderDto()== null){
            errors.add("Can you enter at least a provider order");
        }
        if(providerOrderLineDto.getArticleDto() == null){
            errors.add("Can you enter at least a article");
        }
        if(providerOrderLineDto.getQuantity() == null){
            errors.add("Can you enter a quantity of order");
        }
        if(providerOrderLineDto.getUnitPrice() == null){
            errors.add("Can you enter your unit price");
        }

        return errors;
    }
}
