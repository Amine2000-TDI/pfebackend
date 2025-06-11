package com.ezzahi.pfe_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class AnnouncementPicture {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Announcement announcement;
    private String url;
}
