package com.ezzahi.pfe_backend.controllers;

import com.ezzahi.pfe_backend.dtos.ParticipatingContractDto;
import com.ezzahi.pfe_backend.services.ParticipatingContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participants")
@RequiredArgsConstructor
public class ParticipatingContractController {
    private final ParticipatingContractService participatingContractService;

    @PostMapping("/")
    public ResponseEntity<ParticipatingContractDto> addParticipant(@RequestBody ParticipatingContractDto dto) {
        return ResponseEntity.ok(participatingContractService.save(dto));
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<ParticipatingContractDto>> getParticipants(@PathVariable Long contractId) {
        return ResponseEntity.ok(participatingContractService.getByContractId(contractId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        participatingContractService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
