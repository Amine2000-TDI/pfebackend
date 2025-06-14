package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.enums.AnnonceType;
import com.ezzahi.pfe_backend.models.enums.Status;
import com.ezzahi.pfe_backend.models.enums.TypeLogement;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import lombok.*;

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
    private Long appUserId;
    private String title;
    private String description;
    private String adresse;
    private Double price;
    private Date creationDate;
    private AnnonceType annonceType;
    private TypeLogement typeLogement;
    private Integer nbrPerson;
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
