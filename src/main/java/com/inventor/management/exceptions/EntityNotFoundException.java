package com.inventor.management.exceptions;

import lombok.Getter;

// WE USED WHEN THE ENTITY WASN'T FOUND IN THE DB
public class EntityNotFoundException extends RuntimeException{

    @Getter
    private ErrorCodes errorCodes;

    public EntityNotFoundException (String message){
        super(message);
    }

    public EntityNotFoundException (String message, Throwable cause){
        super(message, cause);
    }

    public EntityNotFoundException (String message, Throwable cause,ErrorCodes errorCodes){
        super(message, cause);
        this.errorCodes = errorCodes;
    }

    public EntityNotFoundException (String message, ErrorCodes errorCodes){
        super(message);
        this.errorCodes = errorCodes;
    }
}
