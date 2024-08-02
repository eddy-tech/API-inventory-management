package com.inventor.management.inventor_management.customer.dto;

import com.inventor.management.core.dto.AddressDto;
import com.inventor.management.inventor_management.enterprise.dto.EnterpriseDto;
import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    private String name;
    private String surname;
    private AddressDto addressDto;
    private String picture;
    private String mail;
    private String numTel;
    private EnterpriseDto id_enterprise;
}
