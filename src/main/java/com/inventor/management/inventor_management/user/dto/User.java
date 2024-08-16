package com.inventor.management.inventor_management.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record User(
        @NotNull(message = "username is required")
        @NotBlank(message = "username is required")
        String username,
        @NotNull(message = "password is required")
        @NotBlank(message = "password is required")
        String password,
        @NotNull(message = "firstName is required")
        @NotBlank(message = "firstName is required")
        String firstName,
        @NotNull(message = "LastName is required")
        @NotBlank(message = "LastName is required")
        String lastName,
        @NotNull(message = "email is required")
        @NotBlank(message = "email is required")
        @Email(message = "your email format is not right")
        String email,
        @NotNull(message = "You have to provide your address")
        @NotBlank(message = "Your address does not to be blank")
        String address,
        @NotNull(message = "BirthDate is required")
        Instant birthDate,
        @NotNull(message = "Picture must not be null")
        String picture,
        @NotNull(message = "You have to provide your enterprise where you work")
        Integer idEnterprise
) {}
