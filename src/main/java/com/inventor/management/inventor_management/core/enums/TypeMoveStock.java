package com.inventor.management.inventor_management.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeMoveStock {
    ENTRANCE("ENTRANCE"),
    EXIT("EXIT"),
    CORRECTION_POSITIVE("CORRECTION_POSITIVE"),
    CORRECTION_NEGATIVE("CORRECTION_NEGATIVE");

    private final String value;
}
