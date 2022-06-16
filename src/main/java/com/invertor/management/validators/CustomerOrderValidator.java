package com.invertor.management.validators;

import com.invertor.management.dto.CustomerOrderDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderValidator {

    public static List<String> validate (CustomerOrderDto customerOrderDto) {
        List<String> errors = new ArrayList<>();

        if(customerOrderDto ==null) {
            errors.add("Can you enter customer order code");
            errors.add("Can you enter you order date");
            errors.add("Can you enter a customer information");

            return errors;
        }
        if(!StringUtils.hasLength(customerOrderDto.getCodeOrderCustomer())){
            errors.add("Can you enter customer order code");
        }
        if(customerOrderDto.getDateOrder() == null){
            errors.add("Can you enter you order date");
        }

        if(customerOrderDto.getCustomerDto() == null) {
            errors.add("Can you enter a customer information");
        }
            return errors;
    }
}
