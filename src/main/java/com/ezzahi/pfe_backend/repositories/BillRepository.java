package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {
}
