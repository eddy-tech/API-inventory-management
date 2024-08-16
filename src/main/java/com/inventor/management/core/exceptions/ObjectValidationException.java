package com.inventor.management.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class ObjectValidationException extends RuntimeException {
    Set<String> violations;
    String violationSource;
}
