package com.ezzahi.pfe_backend.models;

import com.ezzahi.pfe_backend.models.enums.Gender;
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
public class UserDetail {
    @Id
    @GeneratedValue
    private Long id;
    private String phone;
    private LocalDate birthday;
    private LocalDate dateVideoCall;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne()
    private AppUser appUser;
}
