package com.ezzahi.pfe_backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor @Builder
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
    private LocalDate creationDate;
    private LocalDate modificationDate;
}
