package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.ParticipatingContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipatingContractRepository extends JpaRepository<ParticipatingContract,Long> {
   // List<ParticipatingContract> findByAppUserId(Long id);
    Boolean existsByContractIdAndAppUserId(Long contractId,Long userId);

    Optional<ParticipatingContract> findByAppUserId(Long userId);
    List<ParticipatingContract> findByContractId(Long contractId);

}
