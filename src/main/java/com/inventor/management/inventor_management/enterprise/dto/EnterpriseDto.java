package com.inventor.management.inventor_management.enterprise.dto;

import com.inventor.management.inventor_management.core.domains.AbstractEntity;
import com.inventor.management.inventor_management.core.dto.AddressDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class EnterpriseDto {
    private Long id;
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Description is required")
    @NotBlank(message = "Description is required")
    private String description;
    @NotNull(message = "Address is required")
    private AddressDto addressDto;
    @NotNull(message = "Code Fiscal is required")
    @NotBlank(message = "Code Fiscal is required")
    private String codeFiscal;
    @NotNull(message = "Picture is required")
    @NotBlank(message = "Picture is required")
    private String picture;
    @NotNull(message = "Email is required")
    @Email(message = "Customer Email is not a valid email address")
    private String mail;
    @NotNull(message = "Phone number is required")
    private String numTel;
    @NotNull(message = "site web is required")
    private String siteWeb;
}
