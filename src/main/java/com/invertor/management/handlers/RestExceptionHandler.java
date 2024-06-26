package com.invertor.management.handlers;

import com.invertor.management.exceptions.EntityNotFoundException;
import com.invertor.management.exceptions.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException (EntityNotFoundException exception, WebRequest webRequest){

        final HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = ErrorDto.builder()
                .codes(exception.getErrorCodes())
                .httpCode(notFoundStatus.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto,notFoundStatus);

    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handleException (InvalidEntityException exception, WebRequest webRequest){
        final HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;

         final ErrorDto errorDto = ErrorDto.builder()
                .codes(exception.getErrorCodes())
                .httpCode(badRequestStatus.value())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();

         return new ResponseEntity<>(errorDto, badRequestStatus);
    }
}
