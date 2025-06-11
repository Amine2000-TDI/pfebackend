package com.ezzahi.pfe_backend.models;

import com.ezzahi.pfe_backend.models.enums.ApplicationStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Candidacy {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Announcement announcement;
    @ManyToOne
    private AppUser appUser;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private Date applicationDate;
}
