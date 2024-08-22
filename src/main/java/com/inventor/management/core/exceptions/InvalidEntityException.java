package com.inventor.management.core.exceptions;


import lombok.Getter;

import java.util.List;

@Getter
public class InvalidEntityException extends RuntimeException {
    private List<String>errors;

    public InvalidEntityException (String message){
        super(message);
    }
    public InvalidEntityException (String message, List<String> errors){
        super(message);
        this.errors = errors;
    }
}
