package com.ezzahi.pfe_backend.controllers;

import com.ezzahi.pfe_backend.dtos.AnnouncementDto;
import com.ezzahi.pfe_backend.dtos.ContractDto;
import com.ezzahi.pfe_backend.dtos.ContractRegistrationRequest;
import com.ezzahi.pfe_backend.dtos.ParticipatingContractDto;
import com.ezzahi.pfe_backend.exceptions.OperationNonPermittedException;
import com.ezzahi.pfe_backend.services.AnnouncementService;
import com.ezzahi.pfe_backend.services.ContractService;
import com.ezzahi.pfe_backend.services.ParticipatingContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;
    private final AnnouncementService announcementService;
    private final ParticipatingContractService participatingContractService;

    @PostMapping("/")
    public ResponseEntity<ContractDto> create(@RequestBody ContractRegistrationRequest request) {
        if (request.getParticipants() == null || request.getParticipants().isEmpty()) {
            throw new OperationNonPermittedException("Un contrat doit avoir au moins un participant", "createContractWithParticipants", "ContractController");
        }

        //chercher les annonces
        AnnouncementDto announcement = announcementService.getById(request.getAnnouncementId());

        ContractDto contractDto = ContractDto.builder()
                .announcement(announcement)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();

        List<ParticipatingContractDto> listParticipant = request.getParticipants().stream().map(participatingContractService::getById).toList();

        for (ParticipatingContractDto pc : listParticipant) {
            pc.setContract(contractDto);
        }

        return ResponseEntity.ok(contractService.save(contractDto));
    }

    @GetMapping("/announcement/{id}")
    public ResponseEntity<Optional<ContractDto>> getByAnnouncement(@PathVariable Long id) {
        return ResponseEntity.ok(contractService.getContractsByAnnouncementId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contractService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
