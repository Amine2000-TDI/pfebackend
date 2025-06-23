package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.Candidacy;
import com.ezzahi.pfe_backend.models.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidacyRepository extends JpaRepository<Candidacy,Long> {
    List<Candidacy> findByAppUserId(Long appUserId);
    List<Candidacy> findByAnnouncementId(Long announcementId);
    Optional<Candidacy> findByAppUserIdAndAnnouncementId(Long appUserId, Long announcementId);
    List<Candidacy> findByAnnouncementIdAndStatus(Long announcementId, ApplicationStatus status);
    List<Candidacy> findByAppUserIdAndStatus(Long appUserId, ApplicationStatus status);
}
