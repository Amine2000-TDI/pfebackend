package com.ezzahi.pfe_backend.services.impl;

import com.ezzahi.pfe_backend.dtos.ParticipatingContractDto;
import com.ezzahi.pfe_backend.exceptions.NotFoundException;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Contract;
import com.ezzahi.pfe_backend.models.ParticipatingContract;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import com.ezzahi.pfe_backend.repositories.ContractRepository;
import com.ezzahi.pfe_backend.repositories.ParticipatingContractRepository;
import com.ezzahi.pfe_backend.services.ParticipatingContractService;
import com.ezzahi.pfe_backend.validators.ObjectValidators;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipatingContractServiceImpl implements ParticipatingContractService {
    private final ParticipatingContractRepository participatingContractRepository;
    private final AppUserRepository appUserRepository;
    private final ContractRepository contractRepository;
    private final ObjectValidators<ParticipatingContractDto> validator;

    @Override
    public ParticipatingContractDto save(ParticipatingContractDto dto) {
        validator.validate(dto);

        AppUser appUser = appUserRepository.findById(dto.getAppUser().getId())
                .orElseThrow(() -> new NotFoundException("User not found with id : "+dto.getAppUser().getId(), "ParticipatingContract save"));

        Contract contract = contractRepository.findById(dto.getContract().getId())
                .orElseThrow(() -> new NotFoundException("Contract not found with id : "+dto.getContract().getId(), "ParticipatingContract save"));

        // Vérifier si un utilisateur a déjà un contrat (si cette règle est imposée)
        participatingContractRepository.findByAppUserId(appUser.getId()).stream().findAny().ifPresent(existing -> {
            throw new IllegalStateException("The user already has a contract");
        });

        ParticipatingContract pc = ParticipatingContractDto.toEntity(dto, appUser, contract);
        return ParticipatingContractDto.toDto(participatingContractRepository.save(pc));
    }

    @Override
    public ParticipatingContractDto getById(Long id) {
        return participatingContractRepository.findById(id)
                .map(ParticipatingContractDto::toDto)
                .orElseThrow(() -> new NotFoundException("Participating not found with id : "+id, "ParticipatingContract getById"));
    }

    @Override
    public List<ParticipatingContractDto> getAll() {
        return participatingContractRepository.findAll()
                .stream()
                .map(ParticipatingContractDto::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        ParticipatingContract pc = participatingContractRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Participating not found with id : "+id, "ParticipatingContract delete"));
        participatingContractRepository.delete(pc);
    }

    @Override
    public List<ParticipatingContractDto> getByContractId(Long contractId) {
        return participatingContractRepository.findByContractId(contractId)
                .stream()
                .map(ParticipatingContractDto::toDto)
                .toList();
    }

    @Override
    public ParticipatingContractDto getByAppUserId(Long appUserId) {
        return participatingContractRepository.findByAppUserId(appUserId)
                .map(ParticipatingContractDto::toDto)
                .orElseThrow(() -> new NotFoundException("Participating not found for the user "+appUserId, "ParticipatingContract getByAppUserId"));
    }
}
