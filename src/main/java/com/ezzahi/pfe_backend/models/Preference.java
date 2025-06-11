package com.ezzahi.pfe_backend.models;

import jakarta.persistence.*;

@Entity
public class Preference {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private AppUser appUser;
    private Boolean smoker;
    private Boolean dogLover;
    private Boolean catLover;
    private Boolean practicinSport;
    private Boolean practicingReligious;
    private Boolean arabic;
    private Boolean french;
    private Boolean english;
    private Boolean spanich;
}
