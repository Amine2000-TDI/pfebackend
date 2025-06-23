package com.ezzahi.pfe_backend.services.impl;

import com.ezzahi.pfe_backend.dtos.ReportingDto;
import com.ezzahi.pfe_backend.exceptions.NotFoundException;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Reporting;
import com.ezzahi.pfe_backend.models.enums.ReportingStatus;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import com.ezzahi.pfe_backend.repositories.ReportingRepository;
import com.ezzahi.pfe_backend.services.ReportingService;
import com.ezzahi.pfe_backend.validators.ObjectValidators;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportingServiceImpl implements ReportingService {
    private final ReportingRepository reportingRepository;
    private final AppUserRepository appUserRepository;
    private final ObjectValidators<ReportingDto> validator;

    @Override
    public ReportingDto save(ReportingDto dto) {
        validator.validate(dto);

        AppUser author = appUserRepository.findById(dto.getAuthor().getId())
                .orElseThrow(() -> new NotFoundException("The author not found with id : "+dto.getAuthor().getId(), "Reporting"));
        AppUser target = appUserRepository.findById(dto.getTarget().getId())
                .orElseThrow(() -> new NotFoundException("The target not found with id : "+dto.getTarget().getId(), "Reporting"));

        dto.setReportingDate(LocalDate.now());

        Reporting reporting = ReportingDto.toEntity(dto, author, dto.getTarget());
        return ReportingDto.toDto(reportingRepository.save(reporting));
    }

    @Override
    public void delete(Long id) {
        Reporting reporting = reportingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reporting not found with id : "+id, "Reporting"));
        reportingRepository.delete(reporting);
    }

    @Override
    public ReportingDto getById(Long id) {
        return reportingRepository.findById(id)
                .map(ReportingDto::toDto)
                .orElseThrow(() -> new NotFoundException("Reporting not found with id : "+id, "Reporting"));
    }

    @Override
    public List<ReportingDto> getAll() {
        return reportingRepository.findAll().stream()
                .map(ReportingDto::toDto)
                .toList();
    }


    //***************************************************************************************************************
    @Override
    public List<ReportingDto> getReportsByTargetUser(Long userId) {
        return reportingRepository.findByTargetId(userId).stream()
                .map(ReportingDto::toDto)
                .toList();
    }

    @Override
    public List<ReportingDto> getReportsByAuthor(Long authorId) {
        return reportingRepository.findByAuthorId(authorId).stream()
                .map(ReportingDto::toDto)
                .toList();
    }
    @Override
    public ReportingDto updateStatus(Long id, ReportingStatus status) {
        Reporting reporting = reportingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Signalement non trouvé", "Reporting"));
        reporting.setStatus(status);
        return ReportingDto.toDto(reportingRepository.save(reporting));
    }

    @Override
    public long countReportsByUser(Long userId) {
        return reportingRepository.countByTargetId(userId);
    }
}
