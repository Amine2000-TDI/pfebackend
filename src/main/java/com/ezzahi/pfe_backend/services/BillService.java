package com.ezzahi.pfe_backend.services;

import com.ezzahi.pfe_backend.dtos.BillDto;
import com.ezzahi.pfe_backend.models.Bill;

import java.util.List;

public interface BillService extends Crudservice<Bill, BillDto> {
    List<BillDto> getByUserId(Long userId);	//Récupérer toutes les factures d’un utilisateur.
    List<BillDto> getByColocationId(Long colocationId); //	Récupérer les factures d’une colocation spécifique.
    List<BillDto> getUnpaidBillsByUser(Long userId);	//Récupérer les factures non payées d’un utilisateur.
    BillDto markAsPaid(Long id);	//Marquer une facture comme payée (utile pour mise à jour rapide).
    Double getTotalAmountByUser(Long userId);	//Connaître le total dû ou payé par un utilisateur.




}
