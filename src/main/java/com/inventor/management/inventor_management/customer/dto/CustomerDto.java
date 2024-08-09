package com.inventor.management.inventor_management.customer.dto;

import com.inventor.management.inventor_management.core.dto.AddressDto;
import com.inventor.management.inventor_management.enterprise.dto.EnterpriseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Surname is required")
    @NotBlank(message = "Surname is required")
    private String surname;
    private AddressDto addressDto;
    private String picture;
    @NotNull(message = "Email is required")
    @Email(message = "Customer Email is not a valid email address")
    private String mail;
    @NotNull(message = "Phone is required")
    private String numTel;
    private EnterpriseDto id_enterprise;
}
