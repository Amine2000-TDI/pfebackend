package com.ezzahi.pfe_backend.services.impl;

import com.ezzahi.pfe_backend.dtos.ContractDto;
import com.ezzahi.pfe_backend.dtos.ParticipatingContractDto;
import com.ezzahi.pfe_backend.exceptions.NotFoundException;
import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.Contract;
import com.ezzahi.pfe_backend.models.ParticipatingContract;
import com.ezzahi.pfe_backend.repositories.AnnouncementRepository;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import com.ezzahi.pfe_backend.repositories.ContractRepository;
import com.ezzahi.pfe_backend.repositories.ParticipatingContractRepository;
import com.ezzahi.pfe_backend.services.ContractService;
import com.ezzahi.pfe_backend.validators.ObjectValidators;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final AnnouncementRepository announcementRepository;
    private final ParticipatingContractRepository participatingContractRepository;
    private final AppUserRepository appUserRepository;
    private final ObjectValidators<ContractDto> validator;

    @Override
    public ContractDto save(ContractDto dto) {
        validator.validate(dto);
        if (dto.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La date de début doit être aujourd'hui ou dans le futur.");
        }
        if (dto.getEndDate().isBefore(dto.getStartDate())) {
            throw new IllegalArgumentException("La date de fin doit être après la date de début.");
        }

        Announcement announcement = announcementRepository.findById(dto.getAnnouncement().getId())
                .orElseThrow(() -> new NotFoundException("Announcement not found with id : "+dto.getAnnouncement().getId(), "Contract Save"));

        Contract contract = new Contract().builder()
                .announcement(announcement)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();

        List<ParticipatingContract> participatingContracts = new ArrayList<>();
        List<Long> participatingContractsdto = dto.getParticipatingContracts().stream().map(ParticipatingContractDto::getId).toList();

        for (Long pcId : participatingContractsdto) {
            ParticipatingContract pc = participatingContractRepository.findById(pcId)
                    .orElseThrow(() -> new NotFoundException("Participant not found with id : " + pcId, "Contract Save"));
            pc.setContract(contract);
            participatingContracts.add(pc);
        }

        contract.setParticipatingContracts(participatingContracts);
        Contract savedContract = contractRepository.save(contract);

        return ContractDto.toDto(savedContract);
    }

    @Override
    public ContractDto getById(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Contrat not found with id : " + id, "Contract Get"));
        return ContractDto.toDto(contract);
    }

    @Override
    public List<ContractDto> getAll() {
        return contractRepository.findAll().stream()
                .map(ContractDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Contrat not found with id : " + id, "Contract Delete"));
        contractRepository.delete(contract);
    }
    @Override
    public List<ContractDto> getContractsByAnnouncementId(Long announcementId) {
        return contractRepository.findByAnnouncementId(announcementId).stream()
                .map(ContractDto::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<ContractDto> getContractsByUserId(Long userId) {
        return participatingContractRepository.findByAppUserId(userId).stream()
                .map(ParticipatingContract::getContract)
                .distinct()
                .map(ContractDto::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public boolean isUserInContract(Long contractId, Long userId) {
        return participatingContractRepository.existsByContractIdAndAppUserId(contractId, userId);
    }
}
