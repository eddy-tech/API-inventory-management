package com.inventor.management.core.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@Builder
public class ExceptionRepresentation {
    private String errorMessage;
    private String errorSource;
    private Set<String> validationErrors;
}
