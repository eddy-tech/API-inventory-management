package com.inventor.management.core.exceptions;


import lombok.Getter;

import java.util.List;

// TO RECORD IN DB AND WE WANT TO KNOW IF THIS ENTITY HAS BEEN NOT VALID (REFERENCE VALIDATOR)
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
