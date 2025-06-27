package com.ezzahi.pfe_backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserPreferenceRequest {
    private Long id;
    private Long user_id;
    private String description;
    private Boolean smoker;
    private Boolean dogLover;
    private Boolean catLover;
    private Boolean practicingSport;
    private Boolean practicingReligious;
    private Boolean arabic;
    private Boolean french;
    private Boolean english;
    private Boolean spanish;
}
