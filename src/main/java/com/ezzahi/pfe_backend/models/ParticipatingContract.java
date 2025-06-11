package com.ezzahi.pfe_backend.models;

import com.ezzahi.pfe_backend.models.enums.ParticipantType;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class ParticipatingContract {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private AppUser appUser;
    @ManyToOne
    private Contract contract;
    @OneToMany(mappedBy = "ParticipatingContract", cascade = CascadeType.ALL)
    private List<Bill> bills;
    @Enumerated(EnumType.STRING)
    private ParticipantType participantType;
    private Date entryDate;
    private Date exitDate;
}
