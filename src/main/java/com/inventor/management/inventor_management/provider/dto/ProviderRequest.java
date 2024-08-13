package com.inventor.management.inventor_management.provider.dto;

import com.inventor.management.inventor_management.core.dto.AddressDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProviderRequest(
        @NotNull(message = "Name is required")
        @NotBlank(message = "Name is required")
        String name,
        @NotNull(message = "Surname is required")
        @NotBlank(message = "Surname is required")
        String surname,
        @NotNull(message = "Address is required")
        @NotBlank(message = "Address is required")
        AddressDto addressDto,
        String picture,

        @NotNull(message = "Email is required")
        @Email(message = "Provider Email is not a valid email address")
        String mail,
        @NotNull(message = "Phone is required")
        String numTel,
        Long id_enterprise
) {}
