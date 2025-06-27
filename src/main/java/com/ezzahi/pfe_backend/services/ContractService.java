package com.ezzahi.pfe_backend.services;

import com.ezzahi.pfe_backend.dtos.ContractDto;
import com.ezzahi.pfe_backend.models.Contract;

import java.util.List;
import java.util.Optional;

public interface ContractService extends Crudservice<Contract, ContractDto> {
    //List<ContractDto> getByUserId(Long userId);
   // List<ContractDto> getByAnnouncementId(Long announcementId);

    //ContractDto update(Long id, ContractDto dto);
    //List<ContractDto> getCurrentContractsByUser(Long userId);

    List<ContractDto> getContractsByUserId(Long userId);
    Optional<ContractDto> getContractsByAnnouncementId(Long announcementId);
    boolean isUserInContract(Long contractId, Long userId);
}
