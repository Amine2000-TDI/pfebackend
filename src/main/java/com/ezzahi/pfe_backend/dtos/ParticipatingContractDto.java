package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.*;
import com.ezzahi.pfe_backend.models.enums.ParticipantType;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import com.ezzahi.pfe_backend.repositories.ContractRepository;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipatingContractDto {
    private Long id;
    private Long appUserId;
    private Long contractId;
    private ParticipantType participantType;
    private Date entryDate;
    private Date exitDate;

    public static ParticipatingContractDto toDto(ParticipatingContract participatingContract) {
        return ParticipatingContractDto.builder()
                .id(participatingContract.getId())
                .appUserId(participatingContract.getAppUser().getId())
                .contractId(participatingContract.getContract().getId())
                .participantType(participatingContract.getParticipantType())
                .entryDate(participatingContract.getEntryDate())
                .exitDate(participatingContract.getExitDate())
                .build();
    }
    public static ParticipatingContract toEntity(ParticipatingContractDto participatingContractDto, AppUserRepository appUserRepository, ContractRepository contractRepository) {
        AppUser appUser = appUserRepository.findById(participatingContractDto.appUserId)
                .orElseThrow(()-> new RuntimeException("User not found with id " + participatingContractDto.appUserId));
        Contract contract = contractRepository.findById(participatingContractDto.contractId)
                .orElseThrow(()-> new RuntimeException("Contract not found with id " + participatingContractDto.contractId));
        return ParticipatingContract.builder()
                .id(participatingContractDto.getId())
                .appUser(appUser)
                .contract(contract)
                .participantType(participatingContractDto.getParticipantType())
                .entryDate(participatingContractDto.getEntryDate())
                .exitDate(participatingContractDto.getExitDate())
                .build();
    }

}
