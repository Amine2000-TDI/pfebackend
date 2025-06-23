package com.ezzahi.pfe_backend.services;

import com.ezzahi.pfe_backend.dtos.AnnouncementPictureDto;
import com.ezzahi.pfe_backend.models.AnnouncementPicture;

import java.util.List;

public interface AnnouncementPictureService extends Crudservice<AnnouncementPicture, AnnouncementPictureDto>{
    List<AnnouncementPictureDto> findAllByAnnouncementId(Long announcementId);
    void deleteAllByAnnouncementId(Long announcementId);
    AnnouncementPictureDto getMainPictureByAnnouncementId(Long announcementId);
}
