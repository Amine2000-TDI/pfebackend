package com.ezzahi.pfe_backend.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Contract {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Announcement announcement;
    @OneToMany(mappedBy = "contract")
    private List<ParticipatingContract> participatingContracts;
    @OneToMany(mappedBy = "contract")
    private List<Comment> comments;
    private Date StartDate;
    private Date EndDate;

}
