package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.dtos.BillDto;
import com.ezzahi.pfe_backend.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Long> {
    // 1. Récupérer toutes les factures d’un utilisateur
    List<Bill> findByParticipatingContract_AppUser_Id(Long userId);
    // 2. Récupérer les factures d’une colocation spécifique (via Contract)
    List<Bill> findByParticipatingContract_Contract_Id(Long colocationId);
    // 3. Récupérer les factures non payées d’un utilisateur
    List<Bill> findByParticipatingContract_AppUser_IdAndIsPaidFalse(Long userId);
    // 4. Total dû par un utilisateur
    @Query("SELECT SUM(b.amount) FROM Bill b WHERE b.participatingContract.appUser.id = :userId")
    Double getTotalAmountByUser(@Param("userId") Long userId);
}
