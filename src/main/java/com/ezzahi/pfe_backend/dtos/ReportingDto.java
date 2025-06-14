package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Reporting;
import com.ezzahi.pfe_backend.models.enums.ReportingStatus;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportingDto {
    private long id;
    private Long authorId;
    private Long targetId;
    private String description;
    private ReportingStatus status;
    private Date reportingDate;

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
