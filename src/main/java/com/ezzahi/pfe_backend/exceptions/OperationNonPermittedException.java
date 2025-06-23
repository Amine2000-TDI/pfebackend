package com.ezzahi.pfe_backend.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OperationNonPermittedException extends RuntimeException {
    @Getter
    private final String errorMessage;
    @Getter
    private final String operationId;
    @Getter
    private final String source;
}
