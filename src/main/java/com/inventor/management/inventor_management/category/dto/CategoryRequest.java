package com.inventor.management.inventor_management.category.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @NotNull(message = "Designation is required")
        String designation
) {}
