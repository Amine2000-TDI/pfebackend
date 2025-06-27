package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.AnnouncementPicture;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.enums.AnnonceType;
import com.ezzahi.pfe_backend.models.enums.Status;
import com.ezzahi.pfe_backend.models.enums.TypeLogement;
import com.ezzahi.pfe_backend.models.enums.Ville;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
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
    private AppUserDto appUser;
    @NotNull(message = "Les photos sont obligatoires")
    private List<AnnouncementPictureDto> pictures;
    @NotBlank(message = "Le titre est obligatoire")
    @Size(min = 5, max = 100, message = "Le titre doit contenir entre 5 et 100 caractères")
    private String title;
    @NotBlank(message = "La description est obligatoire")
    @Size(min = 10, message = "La description doit contenir au moins 10 caractères")
    private String description;
    @NotBlank(message = "L'adresse est obligatoire")
    @Size(min = 5, max = 100, message = "L'adresse doit contenir entre 5 et 100 caractères")
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
    @Max(value = 15, message = "Il ne doit pas dépasser 15 personnes")
    private Integer nbrPerson;
    @Min(value = 5, message = "la surface ne doit pas être moin de 5m2")
    @Max(value = 1000, message = "la surface ne doit pas être plus de 1000m2")
    private Integer surface;
    private Status status;
    @NotNull(message = "La Ville est obligatoire")
    private Ville ville;

    public static AnnouncementDto toDto(Announcement announcement) {
        List<AnnouncementPictureDto> pictures = announcement.getPictures().stream().map(AnnouncementPictureDto::toDto).toList();
        return AnnouncementDto.builder()
                .id(announcement.getId())
                .appUser(AppUserDto.toDto(announcement.getAppUser()))
                .pictures(pictures)
                .title(announcement.getTitle())
                .ville(announcement.getVille())
                .description(announcement.getDescription())
                .adresse(announcement.getAdresse())
                .price(announcement.getPrice())
                .creationDate(announcement.getCreationDate())
                .annonceType(announcement.getAnnonceType())
                .typeLogement(announcement.getTypeLogement())
                .nbrPerson(announcement.getNbrPerson())
                .surface(announcement.getSurface())
                .status(announcement.getStatus())
                .build();
    }
    public static Announcement toEntity(AnnouncementDto announcementDto, AppUser appUser) {
        Announcement announcement = Announcement.builder()
                .id(announcementDto.getId())
                .appUser(appUser)
                .title(announcementDto.getTitle())
                .description(announcementDto.getDescription())
                .adresse(announcementDto.getAdresse())
                .ville(announcementDto.getVille())
                .price(announcementDto.getPrice())
                .creationDate(announcementDto.getCreationDate())
                .annonceType(announcementDto.getAnnonceType())
                .typeLogement(announcementDto.getTypeLogement())
                .nbrPerson(announcementDto.getNbrPerson())
                .surface(announcementDto.getSurface())
                .status(announcementDto.getStatus())
                .build();
        List<AnnouncementPicture> pictures = announcementDto.getPictures().stream().map(o -> AnnouncementPictureDto.toEntity(o,announcement)).toList();
        announcement.setPictures(pictures);
        return announcement;
    }
}
