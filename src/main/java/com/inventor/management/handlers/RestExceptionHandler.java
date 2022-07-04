package com.inventor.management.handlers;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.exceptions.InvalidOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.ResultSet;
import java.util.Collections;

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
  @ExceptionHandler(FlickrException.class)
    public ResponseEntity<ErrorDto> handleException (FlickrException exception, WebRequest webRequest){
        final HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = ErrorDto.builder()
                .codes(ErrorCodes.valueOf(exception.getErrorCode()))
                .httpCode(notFoundStatus.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto,notFoundStatus);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ErrorDto> handleException (InvalidOperationException exception, WebRequest webRequest){
        final HttpStatus notFoundStatus = HttpStatus.BAD_REQUEST;
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

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto>handleExceptions(BadCredentialsException exception, WebRequest webRequest){
        final HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .codes(ErrorCodes.BAD_CREDENTIALS)
                .httpCode(badRequestStatus.value())
                .message(exception.getMessage())
                .errors(Collections.singletonList("Login and / or wrong password"))
                .build();

        return new ResponseEntity<>(errorDto,badRequestStatus);
    }
}
