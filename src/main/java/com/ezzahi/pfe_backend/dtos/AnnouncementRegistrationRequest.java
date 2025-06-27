package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.AnnouncementPicture;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Candidacy;
import com.ezzahi.pfe_backend.models.Contract;
import com.ezzahi.pfe_backend.models.enums.AnnonceType;
import com.ezzahi.pfe_backend.models.enums.Status;
import com.ezzahi.pfe_backend.models.enums.TypeLogement;
import com.ezzahi.pfe_backend.models.enums.Ville;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AnnouncementRegistrationRequest {
    private Long id;
    private Long user_id;
    private List<AnnouncementPictureDto> pictures;
    private String title;
    private String description;
    private String adresse;
    private Double price;
    private Integer nbrPerson;
    private Integer surface;
    private AnnonceType annonceType;
    private TypeLogement typeLogement;
    private Ville ville;
}
