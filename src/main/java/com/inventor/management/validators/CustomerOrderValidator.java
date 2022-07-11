package com.inventor.management.validators;

import com.inventor.management.dto.CustomerOrderDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderValidator {

    public static List<String> validate (CustomerOrderDto customerOrderDto) {
        List<String> errors = new ArrayList<>();

        if(customerOrderDto ==null) {
            errors.add("Can you enter customer order code");
            errors.add("Can you enter you order date");
            errors.add("Can you enter you state order");
            errors.add("Can you enter a customer information");

            return errors;
        }
        if(!StringUtils.hasLength(customerOrderDto.getCodeOrderCustomer())){
            errors.add("Can you enter customer order code");
        }
        if(customerOrderDto.getDateOrder() == null){
            errors.add("Can you enter you order date");
        }
        if(!StringUtils.hasLength(customerOrderDto.getStateOrder().toString())) {
            errors.add("Can you enter state order");
        }
        if(customerOrderDto.getCustomerDto() == null || customerOrderDto.getCustomerDto().getId() == null) {
            errors.add("Can you enter a customer information");
        }

        return errors;
    }
}
