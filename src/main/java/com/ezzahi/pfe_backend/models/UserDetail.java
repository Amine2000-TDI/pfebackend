package com.ezzahi.pfe_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

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
    private Date birthday;
    private Date dateVideoCall;
    @OneToOne
    private AppUser appUser;
}
