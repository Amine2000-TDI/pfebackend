package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement,Long> {
    List<Announcement> findByAppUserId(Long id);
    List<Announcement> findByStatus(Status status);
    List<Announcement> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
    List<Announcement> findTop10ByOrderByCreationDateDesc();

}
