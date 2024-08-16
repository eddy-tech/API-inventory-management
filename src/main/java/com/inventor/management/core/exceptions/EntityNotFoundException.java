package com.inventor.management.core.exceptions;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException (String message){
        super(message);
    }
}
