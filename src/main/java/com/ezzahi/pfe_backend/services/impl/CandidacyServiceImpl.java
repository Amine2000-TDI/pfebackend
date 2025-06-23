package com.ezzahi.pfe_backend.services.impl;


import com.ezzahi.pfe_backend.dtos.CandidacyDto;
import com.ezzahi.pfe_backend.exceptions.NotFoundException;
import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Candidacy;
import com.ezzahi.pfe_backend.models.enums.ApplicationStatus;
import com.ezzahi.pfe_backend.repositories.AnnouncementRepository;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import com.ezzahi.pfe_backend.repositories.CandidacyRepository;
import com.ezzahi.pfe_backend.services.AnnouncementService;
import com.ezzahi.pfe_backend.services.AppUserService;
import com.ezzahi.pfe_backend.services.CandidacyService;
import com.ezzahi.pfe_backend.validators.ObjectValidators;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CandidacyServiceImpl implements CandidacyService {

    private final CandidacyRepository candidacyRepository;
    private final AppUserService appUserService;
    private final AnnouncementService announcementService;
    private final ObjectValidators<CandidacyDto> validator;
    private final AppUserRepository appUserRepository;
    private final AnnouncementRepository announcementRepository;

    @Override
    public CandidacyDto save(CandidacyDto dto) {
        validator.validate(dto);
        AppUser appUser = appUserRepository.findById(dto.getAppUser().getId())
                .orElseThrow(() -> new NotFoundException("AppUser not found", "Candidacy save"));
        Announcement announcement = announcementRepository.findById(dto.getAnnouncement().getId())
                .orElseThrow(() -> new NotFoundException("Announcement not found", "Candidacy save"));
        if(dto.getApplicationDate() == null) {
            dto.setApplicationDate(LocalDate.now());
            dto.setStatus(ApplicationStatus.PENDING);
        }
        return CandidacyDto.toDto(candidacyRepository.save(CandidacyDto.toEntity(dto,appUser,announcement)));
    }

    @Override
    public void delete(Long id) {
        Candidacy candidacy = candidacyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Candidacy not found with id: " + id,"Candidacy"));
        candidacyRepository.delete(candidacy);
    }

    @Override
    public CandidacyDto getById(Long id) {
        Candidacy candidacy = candidacyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Candidacy not found with id: " + id,"Candidacy"));
        return CandidacyDto.toDto(candidacy);
    }

    @Override
    public List<CandidacyDto> getAll() {
        return candidacyRepository.findAll().stream().map(CandidacyDto::toDto).toList();
    }


    //***************************************************************************************************************************
    @Override
    public List<CandidacyDto> getByAppUserId(Long appUserId) {
        return candidacyRepository.findByAppUserId(appUserId)
                .stream()
                .map(CandidacyDto::toDto)
                .toList();
    }

    @Override
    public List<CandidacyDto> getByAnnouncementId(Long announcementId) {
        return candidacyRepository.findByAnnouncementId(announcementId)
                .stream()
                .map(CandidacyDto::toDto)
                .toList();
    }
    @Override
    public boolean hasAlreadyApplied(Long userId, Long announcementId) {
        return candidacyRepository.findByAppUserIdAndAnnouncementId(userId, announcementId).isPresent();
    }

    @Override
    public void updateStatus(Long candidacyId, ApplicationStatus newStatus) {
        Candidacy candidacy = candidacyRepository.findById(candidacyId)
                .orElseThrow(() -> new NotFoundException("Candidacy not found with id: " + candidacyId, "candidacy Update Status"));
        candidacy.setStatus(newStatus);
        candidacyRepository.save(candidacy);
    }

    @Override
    public List<CandidacyDto> getPendingByAnnouncementId(Long announcementId) {
        return candidacyRepository.findByAnnouncementIdAndStatus(announcementId, ApplicationStatus.PENDING)
                .stream().map(CandidacyDto::toDto).toList();
    }

    @Override
    public List<CandidacyDto> getAcceptedByUserId(Long userId) {
        return candidacyRepository.findByAppUserIdAndStatus(userId, ApplicationStatus.ACCEPT)
                .stream().map(CandidacyDto::toDto).toList();
    }
}
