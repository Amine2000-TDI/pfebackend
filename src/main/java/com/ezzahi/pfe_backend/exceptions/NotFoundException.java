package com.ezzahi.pfe_backend.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotFoundException extends RuntimeException {
    private final String message;
    private final String cause;
}
