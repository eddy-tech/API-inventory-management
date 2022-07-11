package com.inventor.management.validators;

import com.inventor.management.dto.ProviderOrderDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProviderOrderValidator {
    public static List<String> validate (ProviderOrderDto providerOrderDto) {
        List<String> errors = new ArrayList<>();

        if(providerOrderDto ==null) {
            errors.add("Can you enter provider order code");
            errors.add("Can you enter you order date");
            errors.add("Can you enter you state order");
            errors.add("Can you enter at least a provider");

            return errors;
        }
        if(!StringUtils.hasLength(providerOrderDto.getCodeProviderOrder())){
            errors.add("Can you enter provider order code");
        }
        if(providerOrderDto.getDateOrdering() == null){
            errors.add("Can you enter you order date");
        }
        if(!StringUtils.hasLength(providerOrderDto.getStateOrder().toString())){
            errors.add("Can you enter you state order");
        }

        if(providerOrderDto.getProviderDto() == null || providerOrderDto.getProviderDto().getId() == null) {
            errors.add("Can you enter at least a provider");
        }
        return errors;
    }
}
