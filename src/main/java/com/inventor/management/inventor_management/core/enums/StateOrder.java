package com.inventor.management.inventor_management.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StateOrder {
    ON_PROGRESS("IN PROGRESS"),
    VALIDATE("VALIDATE"),
    DELIVERED("DELIVERED"),
    CANCELED("CANCELED");

    private final String name;

}
