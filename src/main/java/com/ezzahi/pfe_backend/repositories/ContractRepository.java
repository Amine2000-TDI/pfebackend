package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract,Long> {
    Optional<Contract> findByAnnouncementId(Long id);
}
