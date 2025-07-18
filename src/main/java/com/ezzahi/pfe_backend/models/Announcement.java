package com.ezzahi.pfe_backend.models;

import com.ezzahi.pfe_backend.models.enums.AnnonceType;
import com.ezzahi.pfe_backend.models.enums.TypeLogement;
import com.ezzahi.pfe_backend.models.enums.Status;
import com.ezzahi.pfe_backend.models.enums.Ville;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor @Builder
@Entity
public class Announcement {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private AppUser appUser;
    @OneToMany(mappedBy = "announcement", fetch = FetchType.EAGER, cascade = CascadeType.ALL , orphanRemoval = true)
    private List<AnnouncementPicture> pictures;
    @OneToMany(mappedBy = "announcement", fetch = FetchType.EAGER, cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Candidacy> candidacies;
    @OneToOne(mappedBy = "announcement")
    private Contract contract;
    private String title;
    private String description;
    private String adresse;
    private Double price;
    private LocalDate creationDate;
    private Integer nbrPerson;
    private Integer surface;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private AnnonceType annonceType;
    @Enumerated(EnumType.STRING)
    private TypeLogement typeLogement;
    @Enumerated(EnumType.STRING)
    private Ville ville;

}
