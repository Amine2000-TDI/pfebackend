package com.ezzahi.pfe_backend.models;

import com.ezzahi.pfe_backend.models.enums.AnnonceType;
import com.ezzahi.pfe_backend.models.enums.TypeLogement;
import com.ezzahi.pfe_backend.models.enums.Status;
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
    private LocalDate creationDate;
    @Enumerated(EnumType.STRING)
    private AnnonceType annonceType;
    @Enumerated(EnumType.STRING)
    private TypeLogement typeLogement;
    private Integer nbrPerson;
    @Enumerated(EnumType.STRING)
    private Status status;
}
