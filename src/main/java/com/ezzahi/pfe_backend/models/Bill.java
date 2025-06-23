package com.ezzahi.pfe_backend.models;

import com.ezzahi.pfe_backend.models.enums.BillStatus;
import com.ezzahi.pfe_backend.models.enums.TypeOfCharge;
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
public class Bill {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private ParticipatingContract participatingContract;
    @Enumerated(EnumType.STRING)
    private TypeOfCharge typeOfCharge;
    private Double amount;
    private LocalDate issueDate;
    private LocalDate paymentDate;
    private BillStatus billStatus;
}
