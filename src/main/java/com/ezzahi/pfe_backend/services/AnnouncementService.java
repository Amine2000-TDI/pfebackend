package com.ezzahi.pfe_backend.services;

import com.ezzahi.pfe_backend.dtos.AnnouncementDto;
import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.enums.Status;

import java.util.List;

public interface AnnouncementService extends Crudservice<Announcement, AnnouncementDto> {
    List<AnnouncementDto> getByUserId(Long userId);
    List<AnnouncementDto> getByStatus(Status status);
    List<AnnouncementDto> searchByKeyword(String keyword);
    List<AnnouncementDto> getRecentAnnouncements();
    void changeStatus(Long id, Status status);
    void suspendAnnouncement(Long id);
}
