package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.enums.EtatCompte;
import com.ezzahi.pfe_backend.models.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AnnouncementStatusUpdate {
    private Long id;
    private Status status;

}
