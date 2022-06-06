package com.invertor.management.validators;

import com.invertor.management.dto.ProviderOrderDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProviderOrderValidator {
    public static List<String> validate (ProviderOrderDto providerOrderDto) {
        List<String> errors = new ArrayList<>();

        if(providerOrderDto ==null) {
            errors.add("Can you enter provider order code");
            errors.add("Can you enter you order date");
            errors.add("Can you enter at least a provider");

            return errors;
        }
        if(!StringUtils.hasLength(providerOrderDto.getCodeProviderOrder())){
            errors.add("Can you enter provider order code");
        }
        if(providerOrderDto.getDateOrdering() == null){
            errors.add("Can you enter you order date");
        }

        if(providerOrderDto.getProviderDto() == null) {
            errors.add("Can you enter at least a provider");
        }
        return errors;
    }
}
