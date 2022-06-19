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
            errors.add("Can you enter your site-web");
            errors.add("Can you please enter your address");

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
        if (!StringUtils.hasLength(enterpriseDto.getSiteWeb())) {
            errors.add("Can you enter your site-web");
        }
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
        }
        return errors;
    }
}
