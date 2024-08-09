package com.inventor.management.inventor_management.core.domains;

public record User(
        String username, String password, String firstName, String lastName, String email
) {}
