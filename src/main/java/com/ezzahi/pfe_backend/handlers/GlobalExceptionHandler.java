package com.ezzahi.pfe_backend.handlers;

import com.ezzahi.pfe_backend.exceptions.*;
import org.hibernate.ObjectDeletedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ DuplicateEntityException.class,
                        NotFoundException.class,
                        OperationNonPermittedException.class,
                        ObjectDeletedException.class,
                        InsufficientPhotosException.class
    })
    public final ResponseEntity<?> handleException(Exception ex) {
        if (ex instanceof DuplicateEntityException) {
            DuplicateEntityException de = (DuplicateEntityException) ex;
            return ResponseEntity.badRequest().body(de);
        } else if (ex instanceof NotFoundException) {
            NotFoundException de = (NotFoundException) ex;
            return ResponseEntity.badRequest().body(de);
        } else if (ex instanceof OperationNonPermittedException) {
            OperationNonPermittedException de = (OperationNonPermittedException) ex;
            return ResponseEntity.badRequest().body(de);
        }else if (ex instanceof ObjectDeletedException) {
            ObjectDeletedException de = (ObjectDeletedException) ex;
            return ResponseEntity.badRequest().body(de);
        }else if (ex instanceof InsufficientPhotosException) {
            InsufficientPhotosException de = (InsufficientPhotosException) ex;
            return ResponseEntity.badRequest().body(de);
        }else {
            String message = "the message : "+ex.getMessage()+" the cause : "+ex.getCause()+" the class : "+ex.getClass();
            return ResponseEntity.badRequest().body(message);
        }
    }

    @ExceptionHandler(ObjectValidationException.class)
    public final ResponseEntity<?> handleObjectValidation(ObjectValidationException ex) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("violations", ex.getViolations());
        errorDetails.put("source", ex.getViolationSource());
        return ResponseEntity.badRequest().body(errorDetails);
    }

}
