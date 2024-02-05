package com.inventor.management.exceptions;

import lombok.Getter;

@Getter
public class InvalidOperationException extends RuntimeException {
    private ErrorCodes errorCodes;

    public InvalidOperationException (String message) {
        super(message);
    }

    public InvalidOperationException (String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOperationException (String message, Throwable cause, ErrorCodes customerOrderNotModifiable) {
        super(message,cause);
        this.errorCodes = customerOrderNotModifiable;
    }

    public InvalidOperationException (String message, ErrorCodes customerOrderNotModifiable) {
        super(message);
        this.errorCodes = customerOrderNotModifiable;
    }
}
