package com.ezzahi.pfe_backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor @Builder
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String libelle;
    @ManyToMany(mappedBy = "roles")
    private List<AppUser> appUsers = new ArrayList<>();

    @Override
    public String toString(){
        return "Role (id : "+ id +", libelle : "+ libelle+")" ;
    }
}
