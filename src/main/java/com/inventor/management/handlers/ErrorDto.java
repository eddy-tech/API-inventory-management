package com.inventor.management.handlers;

import com.inventor.management.exceptions.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
