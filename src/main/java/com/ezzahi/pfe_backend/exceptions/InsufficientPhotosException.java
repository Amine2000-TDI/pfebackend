package com.ezzahi.pfe_backend.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class InsufficientPhotosException extends RuntimeException {
    private final String message;
}
