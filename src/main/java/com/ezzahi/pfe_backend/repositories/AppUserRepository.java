package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
}
