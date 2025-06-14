package com.ezzahi.pfe_backend.services;

import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.AnnouncementPicture;
import com.ezzahi.pfe_backend.repositories.AnnouncementPictureRepositroy;
import com.ezzahi.pfe_backend.repositories.AnnouncementRepositroy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {
    private final AnnouncementRepositroy announcementRepositroy;
    private final AnnouncementPictureRepositroy announcementPictureRepositroy;
    public AnnouncementService(AnnouncementRepositroy announcementRepositroy, AnnouncementPictureRepositroy announcementPictureRepositroy) {
        this.announcementRepositroy = announcementRepositroy;
        this.announcementPictureRepositroy = announcementPictureRepositroy;
    }
    public void addPicturesToAnnouncement(Long announcementId, List<Long> announcementPicturesId) {
        Announcement announcement = announcementRepositroy.findById(announcementId)
                .orElseThrow(() -> new RuntimeException("Annonce non trouvé"));
        if(announcementPicturesId.size() == 0 && announcementPicturesId.size() < 10){
            throw new RuntimeException("Plusieurs photos");
        }
        for(Long pictureId : announcementPicturesId){
            AnnouncementPicture announcementPicture =
                    announcementPictureRepositroy.findById(pictureId).orElse(null) ;
            if(announcementPicture != null && !announcement.getPictures().contains(announcementPicture)){
                announcement.getPictures().add(announcementPicture);
                announcementPicture.setAnnouncement(announcement);
            }
        }
        announcementRepositroy.save(announcement);
    }
}
