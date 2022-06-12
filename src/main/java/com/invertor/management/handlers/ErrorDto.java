package com.invertor.management.handlers;

import com.invertor.management.exceptions.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ErrorDto {
    private Integer httpCode;

    private ErrorCodes codes;

    private String message;

    private List<String> errors = new ArrayList<>();
}
