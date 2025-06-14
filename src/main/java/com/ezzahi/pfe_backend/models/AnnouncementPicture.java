package com.ezzahi.pfe_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor @Builder
@Entity
public class AnnouncementPicture {
    @Id
    @GeneratedValue
    private Long id;
    private String url;
    @ManyToOne
    private Announcement announcement;
}
