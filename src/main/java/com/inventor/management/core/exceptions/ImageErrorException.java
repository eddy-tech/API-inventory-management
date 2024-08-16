package com.inventor.management.core.exceptions;

import java.io.Serial;

public class ImageErrorException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 5003320033602480096L;
    public ImageErrorException(final String message) {
        super(message);
    }
}
