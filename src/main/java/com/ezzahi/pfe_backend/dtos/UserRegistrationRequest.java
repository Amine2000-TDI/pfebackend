package com.ezzahi.pfe_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
public class UserRegistrationRequest {
    private Long id;
    private AppUserDto user;
    private String phone;
    private LocalDate birthday;
    private LocalDate dateVideoCall;
}
