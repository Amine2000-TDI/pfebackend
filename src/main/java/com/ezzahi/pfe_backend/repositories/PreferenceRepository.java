package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference,Long> {
}
