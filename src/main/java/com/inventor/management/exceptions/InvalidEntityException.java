package com.inventor.management.exceptions;


import lombok.Getter;

import java.util.List;

// TO RECORD IN DB AND WE WANT TO KNOW IF THIS ENTITY HAS BEEN NOT VALID (REFERENCE VALIDATOR)
@Getter
public class InvalidEntityException extends RuntimeException {
    private ErrorCodes errorCodes;
    private List<String>errors;

    public InvalidEntityException (String message){
        super(message);
    }

    public InvalidEntityException (String message, Throwable cause){
        super(message, cause);
    }

    public InvalidEntityException (String message, ErrorCodes errorCodes){
        super(message);
        this.errorCodes = errorCodes;
    }

    public InvalidEntityException (String message, Throwable cause, ErrorCodes errorCodes){
        super(message, cause);
        this.errorCodes = errorCodes;
    }

    public InvalidEntityException (String message, ErrorCodes errorCodes, List<String> errors){
        super(message);
        this.errorCodes = errorCodes;
        this.errors = errors;
    }
}
