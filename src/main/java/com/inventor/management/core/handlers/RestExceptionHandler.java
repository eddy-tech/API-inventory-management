package com.inventor.management.core.handlers;

import com.inventor.management.core.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.inventor.management.inventor_management.core.utils.Constants.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ObjectValidationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException (ObjectValidationException exception) {
        var representation = ExceptionRepresentation.builder()
                .errorSource(OBJECT_EXCEPTION)
                .errorMessage(exception.getViolationSource())
                .validationErrors(exception.getViolations())
                .build();

        return ResponseEntity.status(BAD_REQUEST).body(representation);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionRepresentation> handleEntityException (EntityNotFoundException exception) {
        var representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getMessage())
                .build();

        return ResponseEntity.status(NOT_FOUND).body(representation);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionRepresentation> handleBusinessException (BusinessException exception) {
        var representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getMessage())
                .build();

        return ResponseEntity.status(NOT_FOUND).body(representation);
    }

    @ExceptionHandler(ImageErrorException.class)
    public ResponseEntity<ExceptionRepresentation> handleFlickrException (ImageErrorException exception){
        var representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getMessage())
                .build();

        return ResponseEntity.status(NOT_FOUND).body(representation);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ExceptionRepresentation> handleOperationException (InvalidOperationException exception){
        var representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getMessage())
                .build();

        return ResponseEntity.status(BAD_REQUEST).body(representation);
    }

    @ExceptionHandler(InvalidEntityException.class)
        public ResponseEntity<ExceptionRepresentation> handleInvalidException (InvalidEntityException exception) {
        var representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getMessage())
                .build();

        return ResponseEntity.status(BAD_REQUEST).body(representation);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionRepresentation> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        var representation = ExceptionRepresentation.builder()
               .errorMessage(USER_EXITS_EXCEPTION)
                .errorSource(exception.getMessage())
               .build();

        return ResponseEntity.status(CONFLICT).body(representation);
    }
}
