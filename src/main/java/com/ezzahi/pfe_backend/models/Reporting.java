package com.ezzahi.pfe_backend.models;

import com.ezzahi.pfe_backend.models.enums.ReportingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor @Builder
@Entity
public class Reporting {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private AppUser author;
    @ManyToOne
    private AppUser target;
    private String description;
    @Enumerated(EnumType.STRING)
    private ReportingStatus status;
    private Date reportingDate;


}
