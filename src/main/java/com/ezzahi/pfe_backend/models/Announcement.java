package com.ezzahi.pfe_backend.models;

import com.ezzahi.pfe_backend.models.enums.AnnonceType;
import com.ezzahi.pfe_backend.models.enums.TypeLogement;
import com.ezzahi.pfe_backend.models.enums.Status;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Announcement {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private AppUser appUser;
    @OneToMany(mappedBy = "announcement", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AnnouncementPicture> pictures;
    @OneToMany(mappedBy = "announcement", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Candidacy> candidacies;
    @OneToOne(mappedBy = "announcement")
    private Contract contract;
    private String title;
    private String description;
    private String adresse;
    private Double price;
    @Enumerated(EnumType.STRING)
    private AnnonceType annonceType;
    private TypeLogement typeLogement;
    private Integer nbrPerson;
    private Status status;
}
