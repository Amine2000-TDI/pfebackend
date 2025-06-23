package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.dtos.AppUserDto;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Optional<Role> findBylibelle(String libelle);
}
