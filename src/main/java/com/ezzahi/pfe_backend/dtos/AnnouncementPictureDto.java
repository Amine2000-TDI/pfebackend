package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.AnnouncementPicture;
import com.ezzahi.pfe_backend.repositories.AnnouncementRepositroy;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnouncementPictureDto {
    private Long id;
    private Long announcementId;
    private String url;
    public static AnnouncementPictureDto toDto(AnnouncementPicture announcementPicture) {
        return AnnouncementPictureDto.builder()
                .id(announcementPicture.getId())
                .announcementId(announcementPicture.getAnnouncement().getId())
                .url(announcementPicture.getUrl())
                .build();
    }
    public static AnnouncementPicture toEntity(AnnouncementPictureDto announcementPictureDto, AnnouncementRepositroy announcementRepositroy) {
        Announcement announcement = announcementRepositroy.findById(announcementPictureDto.getAnnouncementId())
                .orElseThrow(()-> new RuntimeException("Announcement not found with id : " + announcementPictureDto.getAnnouncementId()));
        return AnnouncementPicture.builder()
                .announcement(announcement)
                .url(announcementPictureDto.getUrl())
                .build();
    }
}
