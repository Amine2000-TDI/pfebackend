package com.ezzahi.pfe_backend.services;

import com.ezzahi.pfe_backend.dtos.ParticipatingContractDto;
import com.ezzahi.pfe_backend.models.ParticipatingContract;

import java.util.List;

public interface ParticipatingContractService extends Crudservice<ParticipatingContract, ParticipatingContractDto> {
    List<ParticipatingContractDto> getByContractId(Long contractId);
    ParticipatingContractDto getByAppUserId(Long appUserId);
}
