package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Candidacy;
import com.ezzahi.pfe_backend.models.Comment;
import com.ezzahi.pfe_backend.models.enums.ApplicationStatus;
import com.ezzahi.pfe_backend.repositories.AnnouncementRepositroy;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidacyDto {
    private Long id;
    @NotNull(message = "L'annonce est obligatoire")
    private Long announcementId;
    @NotNull(message = "L'utilisateur est obligatoire")
    private Long appUserId;
    //couche service
    private LocalDate applicationDate;
    //couche service
    private ApplicationStatus status;


    public static CandidacyDto toDto(Candidacy candidancy) {
        return CandidacyDto.builder()
                .id(candidancy.getId())
                .announcementId(candidancy.getAnnouncement().getId())
                .appUserId(candidancy.getAppUser().getId())
                .applicationDate(candidancy.getApplicationDate())
                .status(candidancy.getStatus())
                .build();
    }
    public static Candidacy toEntity(CandidacyDto candidancyDto, AppUserRepository appUserRepository, AnnouncementRepositroy   announcementRepositroy) {
        AppUser appUser = appUserRepository.findById(candidancyDto.getAppUserId())
                .orElseThrow(()-> new RuntimeException("User not found with id +" + candidancyDto.getAppUserId()));
        Announcement announcement = announcementRepositroy.findById(candidancyDto.getAnnouncementId())
                .orElseThrow(()-> new RuntimeException("User not found with id +" + candidancyDto.getAnnouncementId()));
        return Candidacy.builder()
                .id(candidancyDto.getId())
                .appUser(appUser)
                .applicationDate(candidancyDto.getApplicationDate())
                .status(candidancyDto.getStatus())
                .announcement(announcement)
                .build();

    }
}
