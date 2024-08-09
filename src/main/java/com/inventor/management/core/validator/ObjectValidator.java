package com.inventor.management.core.validator;

import com.inventor.management.core.exceptions.ObjectValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ObjectValidator {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public <T> void validate(T objectToValidator) {
        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidator);
        if(!violations.isEmpty()) {
            var errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());

            throw new ObjectValidationException(errorMessage, objectToValidator.toString());
        }
    }
}
