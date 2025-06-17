package com.ezzahi.pfe_backend.models;

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
    private LocalDate startDate;
    private LocalDate endDate;

}
