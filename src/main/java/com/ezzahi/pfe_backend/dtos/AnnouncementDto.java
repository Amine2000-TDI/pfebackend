package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.enums.AnnonceType;
import com.ezzahi.pfe_backend.models.enums.Status;
import com.ezzahi.pfe_backend.models.enums.TypeLogement;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnouncementDto {
    private Long id;
    @NotNull(message = "L'utilisateur est obligatoire")
    private Long appUserId;
    @NotBlank(message = "Le titre est obligatoire")
    @Size(min = 5, max = 100, message = "Le titre doit contenir entre 5 et 100 caractères")
    private String title;
    @NotBlank(message = "La description est obligatoire")
    @Size(min = 10, message = "La description doit contenir au moins 10 caractères")
    private String description;
    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;
    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit être supérieur à 0")
    private Double price;
    // généralement générée automatiquement côté serveur
    private LocalDate creationDate;
    @NotNull(message = "Le type d'annonce est obligatoire")
    private AnnonceType annonceType;
    @NotNull(message = "Le type de logement est obligatoire")
    private TypeLogement typeLogement;
    @NotNull(message = "Le nombre de personnes est obligatoire")
    @Min(value = 1, message = "Il doit y avoir au moins une personne")
    private Integer nbrPerson;
    @NotNull(message = "Le statut est obligatoire")
    private Status status;

    public static AnnouncementDto toDto(Announcement announcement) {
        return AnnouncementDto.builder()
                .id(announcement.getId())
                .appUserId(announcement.getAppUser().getId())
                .title(announcement.getTitle())
                .description(announcement.getDescription())
                .adresse(announcement.getAdresse())
                .price(announcement.getPrice())
                .creationDate(announcement.getCreationDate())
                .annonceType(announcement.getAnnonceType())
                .typeLogement(announcement.getTypeLogement())
                .nbrPerson(announcement.getNbrPerson())
                .status(announcement.getStatus())
                .build();
    }
    public static Announcement toEntity(AnnouncementDto announcementDto, AppUserRepository appUserRepository) {
        AppUser appUser = appUserRepository.findById(announcementDto.getAppUserId())
                .orElseThrow(()-> new RuntimeException("User not found with id +" + announcementDto.getAppUserId()));
        return Announcement.builder()
                .id(announcementDto.getId())
                .appUser(appUser)
                .title(announcementDto.getTitle())
                .description(announcementDto.getDescription())
                .adresse(announcementDto.getAdresse())
                .price(announcementDto.getPrice())
                .creationDate(announcementDto.getCreationDate())
                .annonceType(announcementDto.getAnnonceType())
                .typeLogement(announcementDto.getTypeLogement())
                .nbrPerson(announcementDto.getNbrPerson())
                .status(announcementDto.getStatus())
                .build();

    }
}
