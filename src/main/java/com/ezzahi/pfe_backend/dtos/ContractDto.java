package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.Comment;
import com.ezzahi.pfe_backend.models.Contract;
import com.ezzahi.pfe_backend.models.ParticipatingContract;
import com.ezzahi.pfe_backend.repositories.AnnouncementRepositroy;
import com.ezzahi.pfe_backend.repositories.ParticipatingContractRepository;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDto {
    private Long id;
    @NotNull(message = "L'annonce est obligatoire")
    private Long announcementId ;
    @NotEmpty(message = "La liste des participants ne peut pas être vide")
    private List<@NotNull(message = "L'id du participant ne peut pas être nul") Long> participatingContractsId;
    @NotNull(message = "La date de début est obligatoire")
    //@FutureOrPresent(message = "La date de début doit être aujourd'hui ou dans le futur")
    // ****la date est en future tu dois la gérer en service
    private LocalDate startDate;
    @NotNull(message = "La date de fin est obligatoire")
    private LocalDate endDate;

    public static ContractDto toDto(Contract contract) {
        return ContractDto.builder()
                .id(contract.getId())
                .announcementId(contract.getAnnouncement().getId())
                .participatingContractsId(contract.getParticipatingContracts().stream().map(ParticipatingContract::getId).collect(Collectors.toList()))
                .startDate(contract.getStartDate())
                .endDate(contract.getEndDate())
                .build();
    }

    public static Contract toEntity(ContractDto contractDto, AnnouncementRepositroy announcementRepositroy, ParticipatingContractRepository participatingContractRepository) {
        Announcement announcement = announcementRepositroy.findById(contractDto.getAnnouncementId())
                .orElseThrow(()-> new RuntimeException("Announcement not found with id : " + contractDto.getAnnouncementId()));
        List<ParticipatingContract> participatingContractList = contractDto.getParticipatingContractsId().stream()
                .map(id -> participatingContractRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Participating Contract not found with id : " + id)))
                .toList();
        return Contract.builder()
                .id(contractDto.getId())
                .announcement(announcement)
                .participatingContracts(participatingContractList)
                .startDate(contractDto.getStartDate())
                .endDate(contractDto.getEndDate())
                .build();
    }

}
