package com.ezzahi.pfe_backend.models;

import com.ezzahi.pfe_backend.models.enums.BillStatus;
import com.ezzahi.pfe_backend.models.enums.TypeOfCharge;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Bill {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private ParticipatingContract ParticipatingContract;
    @Enumerated(EnumType.STRING)
    private TypeOfCharge typeOfCharge;
    private Double amount;
    private Date issueDate;
    private Date payementDate;
    private BillStatus billStatus;
}
