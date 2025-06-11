package com.ezzahi.pfe_backend.models;

import com.ezzahi.pfe_backend.models.enums.EtatCompte;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AppUser {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;
    @Enumerated(EnumType.STRING)
    private EtatCompte etat;
    private String password;
    private String photoUrl;
    @ManyToMany(mappedBy = "appUsers")
    private List<Role> roles = new ArrayList<>();
    @OneToOne(mappedBy = "appUser")
    private Preference preference;
    @OneToMany(mappedBy = "appUser")
    private List<Announcement> announcement;
    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<Candidacy> candidacies;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Reporting> reportsSent;
    @OneToMany(mappedBy = "target", cascade = CascadeType.ALL)
    private List<Reporting> reportsReceived;
    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    private ParticipatingContract participatingContract;
}
