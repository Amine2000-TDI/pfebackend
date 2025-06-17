package com.ezzahi.pfe_backend.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor @Builder
@Entity
public class Preference {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private AppUser appUser;
    private String description;
    private Boolean smoker;
    private Boolean dogLover;
    private Boolean catLover;
    private Boolean practicinSport;
    private Boolean practicingReligious;
    private Boolean arabic;
    private Boolean french;
    private Boolean english;
    private Boolean spanish;
}
