package com.ezzahi.pfe_backend.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String libelle;
    @ManyToMany
    @JoinTable(
            name = "UserRole",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "app_user_id"))
    private List<AppUser> appUsers = new ArrayList<>();
}
