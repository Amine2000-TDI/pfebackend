package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.dtos.AnnouncementPictureDto;
import com.ezzahi.pfe_backend.models.AnnouncementPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnnouncementPictureRepository extends JpaRepository<AnnouncementPicture,Long> {
    public List<AnnouncementPicture> findByAnnouncementId(Long id);
    Optional<AnnouncementPicture> findFirstByAnnouncementId(Long announcementId);
}
