package com.ezzahi.pfe_backend.services;

import com.ezzahi.pfe_backend.dtos.CandidacyDto;
import com.ezzahi.pfe_backend.models.Candidacy;
import com.ezzahi.pfe_backend.models.enums.ApplicationStatus;

import java.util.List;

public interface CandidacyService extends Crudservice<Candidacy, CandidacyDto>{
    List<CandidacyDto> getByAnnouncementId(Long announcementId);
    List<CandidacyDto> getByAppUserId(Long appUserId);
    boolean hasAlreadyApplied(Long userId, Long announcementId);
    void updateStatus(Long candidacyId, ApplicationStatus newStatus);
    List<CandidacyDto> getPendingByAnnouncementId(Long announcementId);
    List<CandidacyDto> getAcceptedByUserId(Long userId);
}
