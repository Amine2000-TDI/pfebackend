package com.ezzahi.pfe_backend.models;

import com.ezzahi.pfe_backend.models.enums.ParticipantType;
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
public class ParticipatingContract {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private AppUser appUser;
    @ManyToOne
    private Contract contract;
    @OneToMany(mappedBy = "participatingContract", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Bill> bills;
    @Enumerated(EnumType.STRING)
    private ParticipantType participantType;
    private LocalDate entryDate;
    private LocalDate exitDate;
}
