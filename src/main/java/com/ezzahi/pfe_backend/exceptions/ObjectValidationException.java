package com.ezzahi.pfe_backend.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;
@RequiredArgsConstructor
@Getter
public class ObjectValidationException extends RuntimeException {
    private final Set<String> violations;
    private final String violationSource;

    @Override
    public String getMessage() {
        return "Validation failed in " + violationSource + " with violations: " + violations;
    }

}
