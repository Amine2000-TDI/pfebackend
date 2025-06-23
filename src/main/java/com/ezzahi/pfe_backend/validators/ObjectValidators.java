package com.ezzahi.pfe_backend.validators;

import com.ezzahi.pfe_backend.exceptions.ObjectValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
@Component
public class ObjectValidators<T> {
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    public void validate(T objectToValidate) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(objectToValidate);
        if(!constraintViolations.isEmpty()) {
            Set<String> errorMessages = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
            throw new ObjectValidationException(errorMessages, objectToValidate.getClass().getName());
        }
    }
}


