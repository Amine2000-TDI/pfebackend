package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.enums.EtatCompte;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StatUpdateRequest {
    private Long id;
    private EtatCompte etat;
}
