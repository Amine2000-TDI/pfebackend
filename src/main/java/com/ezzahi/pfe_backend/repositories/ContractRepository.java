package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract,Long> {
}
