package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Reporting;
import com.ezzahi.pfe_backend.models.enums.ReportingStatus;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportingDto {
    private long id;
    @NotNull(message = "L'auteur est requis")
    private Long authorId;
    @NotNull(message = "La cible est requise")
    private Long targetId;
    @NotBlank(message = "La description ne peut pas être vide")
    @Size(min = 2, max = 350, message = "La description doit contenir entre 2 et 350 caractères")
    private String description;
    @NotNull(message = "Le statut est requis")
    private ReportingStatus status;
    //dans la couche service on va initilaiser la date
    private LocalDate reportingDate;

    public static ReportingDto toDto(Reporting reporting) {
        return ReportingDto.builder()
                .id(reporting.getId())
                .authorId(reporting.getAuthor().getId())
                .targetId(reporting.getTarget().getId())
                .description(reporting.getDescription())
                .status(reporting.getStatus())
                .reportingDate(reporting.getReportingDate())
                .build();
    }
    public static Reporting toEntity(ReportingDto reportingDto, AppUserRepository appUserRepository) {
        AppUser authorUser = appUserRepository.findById(reportingDto.getAuthorId())
                .orElseThrow(()-> new RuntimeException("User not found with id +" + reportingDto.getAuthorId()));
        AppUser authorTarget = appUserRepository.findById(reportingDto.getTargetId())
                .orElseThrow(()-> new RuntimeException("User not found with id +" + reportingDto.getTargetId()));
        return Reporting.builder()
                .id(reportingDto.getId())
                .author(authorUser)
                .target(authorTarget)
                .description(reportingDto.getDescription())
                .status(reportingDto.getStatus())
                .reportingDate(reportingDto.getReportingDate())
                .build();

    }
}
