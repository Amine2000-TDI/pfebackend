package com.ezzahi.pfe_backend.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DuplicateEntityException extends RuntimeException {
    private final String message;
    private final String source;
}
