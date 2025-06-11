package com.ezzahi.pfe_backend.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Contract contract;
    @OneToOne
    private AppUser author;
    @OneToOne
    private AppUser target;
    private String comment;
    private Date creationDate;
    private Date modificationDate;
}
