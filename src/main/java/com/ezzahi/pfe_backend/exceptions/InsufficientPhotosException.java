package com.ezzahi.pfe_backend.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InsufficientPhotosException extends RuntimeException {
    private final String message;
}
