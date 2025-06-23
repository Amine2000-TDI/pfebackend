package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Candidacy;
import com.ezzahi.pfe_backend.models.enums.ApplicationStatus;
import com.ezzahi.pfe_backend.repositories.AnnouncementRepository;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidacyDto {
    private Long id;
    @NotNull(message = "L'annonce est obligatoire")
    private AnnouncementDto announcement;
    @NotNull(message = "L'utilisateur est obligatoire")
    private AppUserDto appUser;
    //couche service
    private LocalDate applicationDate;
    //couche service
    private ApplicationStatus status;


    public static CandidacyDto toDto(Candidacy candidancy) {
        return CandidacyDto.builder()
                .id(candidancy.getId())
                .announcement(AnnouncementDto.toDto(candidancy.getAnnouncement()))
                .appUser(AppUserDto.toDto(candidancy.getAppUser()))
                .applicationDate(candidancy.getApplicationDate())
                .status(candidancy.getStatus())
                .build();
    }
    public static Candidacy toEntity(CandidacyDto candidancyDto, AppUser appUser, Announcement announcement) {
        return Candidacy.builder()
                .id(candidancyDto.getId())
                .appUser(appUser)
                .announcement(announcement)
                .applicationDate(candidancyDto.getApplicationDate())
                .status(candidancyDto.getStatus())
                .build();

    }
}
