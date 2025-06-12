package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.Candidacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidacyRepository extends JpaRepository<Candidacy,Long> {
}
