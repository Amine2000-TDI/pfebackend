package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepositroy extends JpaRepository<Announcement,Long> {
}
