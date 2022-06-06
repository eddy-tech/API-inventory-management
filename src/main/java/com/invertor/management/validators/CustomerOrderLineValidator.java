package com.invertor.management.validators;

import com.invertor.management.dto.CustomerOrderLineDto;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderLineValidator {

    public static List<String> validate (CustomerOrderLineDto customerOrderLineDto){
        List<String> errors = new ArrayList<>();

        if(customerOrderLineDto == null){
            errors.add("Can you enter at least a customer order");
            errors.add("Can you enter at least a article");
            errors.add("Can you enter a quantity of order");
            errors.add("Can you enter your unit price");

            return errors;
        }

        if(customerOrderLineDto.getCustomerOrderDto() == null){
            errors.add("Can you enter at least a customer order");
        }
        if(customerOrderLineDto.getArticleDto() == null){
            errors.add("Can you enter at least a article");
        }
        if(customerOrderLineDto.getQuantity() == null){
            errors.add("Can you enter a quantity of order");
        }
        if(customerOrderLineDto.getUnitPrice() == null){
            errors.add("Can you enter your unit price");
        }

        return errors;
    }
}
