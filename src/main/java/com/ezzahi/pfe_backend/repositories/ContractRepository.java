package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract,Long> {
    List<Contract> findByAnnouncementId(Long id);
}
