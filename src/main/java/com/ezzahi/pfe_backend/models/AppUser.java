package com.ezzahi.pfe_backend.models;

import com.ezzahi.pfe_backend.models.enums.EtatCompte;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter @Setter @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor @Builder
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
    private Date dateCreation;
    @ManyToMany(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "UserRole",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Preference preference;
    @OneToMany(mappedBy = "appUser" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY , orphanRemoval = true)
    private List<Announcement> announcements = new ArrayList<>();
    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY , orphanRemoval = true)
    private List<Candidacy> candidacies;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Reporting> reportsSent;
    @OneToMany(mappedBy = "target", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Reporting> reportsReceived;
    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL , orphanRemoval = true)
    private ParticipatingContract participatingContract;

    @Override
    public String toString() {
        return "User (id : " + id + ", username : " + username + ", email : " + email + ", etat : " + etat +", photoUrl : " + photoUrl+")";
    }
}
