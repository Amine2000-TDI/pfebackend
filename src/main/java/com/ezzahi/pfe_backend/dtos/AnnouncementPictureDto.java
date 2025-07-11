package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.AnnouncementPicture;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnouncementPictureDto {
    private Long id;
    @NotNull(message = "L'annonce est obligatoire")
    private Long announcementId;
    @NotBlank(message = "L'URL de l'image est obligatoire")
    private String url;

    public static AnnouncementPictureDto toDto(AnnouncementPicture announcementPicture) {
        return AnnouncementPictureDto.builder()
                .id(announcementPicture.getId())
                .announcementId(announcementPicture.getAnnouncement().getId())
                .url(announcementPicture.getUrl())
                .build();
    }
    public static AnnouncementPicture toEntity(AnnouncementPictureDto announcementPictureDto, Announcement announcement) {
        return AnnouncementPicture.builder()
                .announcement(announcement)
                .url(announcementPictureDto.getUrl())
                .build();
    }
}
