package com.inventor.management.inventor_management.core.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressDto {
    @NotNull(message = "address is required")
    @NotBlank(message = "address is required")
    private String address1;
    private String address2;

    @NotNull(message = "city is required")
    @NotBlank(message = "city is required")
    private String city;

    @NotNull(message = "code postal is required")
    @NotBlank(message = "code postal is required")
    private String codePostal;

    @NotNull(message = "country is required")
    @NotBlank(message = "country is required")
    private String country;
}
