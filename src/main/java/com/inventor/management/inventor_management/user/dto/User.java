package com.inventor.management.inventor_management.user.dto;

public record User(
        String username, String password, String firstName, String lastName, String email
) {}
