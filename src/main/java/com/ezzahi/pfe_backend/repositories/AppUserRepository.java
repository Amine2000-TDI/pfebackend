package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    public Optional<AppUser> findByUsername(String username);
}
