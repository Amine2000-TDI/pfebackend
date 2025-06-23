package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.Contract;
import com.ezzahi.pfe_backend.models.ParticipatingContract;
import com.ezzahi.pfe_backend.repositories.AnnouncementRepository;
import com.ezzahi.pfe_backend.repositories.ParticipatingContractRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
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
    private AnnouncementDto announcement ;
    @NotEmpty(message = "La liste des participants ne peut pas être vide")
    private List<@NotNull(message = "Le participant ne peut pas être nul") ParticipatingContractDto> participatingContracts;
    @NotNull(message = "La date de début est obligatoire")
    //@FutureOrPresent(message = "La date de début doit être aujourd'hui ou dans le futur")
    // ****la date est en futur tu dois la gérer en service
    private LocalDate startDate;
    @NotNull(message = "La date de fin est obligatoire")
    private LocalDate endDate;

    public static ContractDto toDto(Contract contract) {
        return ContractDto.builder()
                .id(contract.getId())
                .announcement(AnnouncementDto.toDto(contract.getAnnouncement()))
                .participatingContracts(contract.getParticipatingContracts().stream().map(ParticipatingContractDto::toDto).toList())
                .startDate(contract.getStartDate())
                .endDate(contract.getEndDate())
                .build();
    }

    public static Contract toEntity(ContractDto contractDto, Announcement announcement,List<ParticipatingContract> participatingContracts) {
        return Contract.builder()
                .id(contractDto.getId())
                .announcement(announcement)
                .participatingContracts(participatingContracts)
                .startDate(contractDto.getStartDate())
                .endDate(contractDto.getEndDate())
                .build();
    }

}
