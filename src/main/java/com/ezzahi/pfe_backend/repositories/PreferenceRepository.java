package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferenceRepository extends JpaRepository<Preference,Long> {
    Optional<Preference> findByAppUserId(Long userId);
}
