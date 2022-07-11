package com.inventor.management.validators;

import com.inventor.management.dto.EnterpriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EnterpriseValidator {

    public static List<String> validate(EnterpriseDto enterpriseDto) {
        List<String> errors = new ArrayList<>();

        if(enterpriseDto == null) {
            errors.add("Can you enter your enterprise name");
            errors.add("Can you enter your description");
            errors.add("Can you enter your fiscal code");
            errors.add("Can you enter your phone number");
            errors.add("Can you enter your e-mail");
            errors.addAll(AddressValidator.validate(null));

            return errors;
        }

        if (!StringUtils.hasLength(enterpriseDto.getName())) {
            errors.add("Can you enter your enterprise name");
        }
        if (!StringUtils.hasLength(enterpriseDto.getDescription())) {
            errors.add("Can you enter your description");
        }
        if (!StringUtils.hasLength(enterpriseDto.getCodeFiscal())) {
            errors.add("Can you enter your fiscal code");
        }
        if (!StringUtils.hasLength(enterpriseDto.getNumTel())) {
            errors.add("Can you enter your phone number");
        }
        if (!StringUtils.hasLength(enterpriseDto.getMail())) {
            errors.add("Can you enter your e-mail");
        }

       /*
        if (enterpriseDto.getAddressDto() == null) {

            errors.add("Can you please enter your address");
        } else {
            if (!StringUtils.hasLength(enterpriseDto.getAddressDto().getAddress1())) {
                errors.add("Field 'Address 1' is required");
            }
            if (!StringUtils.hasLength(enterpriseDto.getAddressDto().getCity())) {
                errors.add("Field 'City' is required");
            }
            if (!StringUtils.hasLength(enterpriseDto.getAddressDto().getCountry())) {
                errors.add("Field 'Country' is required");
            }
            if (!StringUtils.hasLength(enterpriseDto.getAddressDto().getCodePostal())) {
                errors.add("Field 'Code Postal' is required");
            }
            */

        errors.addAll(AddressValidator.validate(enterpriseDto.getAddressDto()));
        return errors;
    }
}
