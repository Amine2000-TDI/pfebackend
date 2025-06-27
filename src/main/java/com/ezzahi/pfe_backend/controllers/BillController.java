package com.ezzahi.pfe_backend.controllers;

import com.ezzahi.pfe_backend.dtos.BillDto;
import com.ezzahi.pfe_backend.services.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {
    private final BillService billService;

    @PostMapping("/")
    public ResponseEntity<BillDto> create(@RequestBody BillDto dto) {
        return ResponseEntity.ok(billService.save(dto));
    }

    @GetMapping("/participant/{id}")
    public ResponseEntity<List<BillDto>> getByParticipant(@PathVariable Long id) {
        return ResponseEntity.ok(billService.getByColocationId(id));
    }

    @GetMapping("/user/{userId}/unpaid")
    public ResponseEntity<List<BillDto>> getUnpaid(@PathVariable Long userId) {
        return ResponseEntity.ok(billService.getUnpaidBillsByUser(userId));
    }

    @PutMapping("/pay/{id}")
    public ResponseEntity<Void> markAsPaid(@PathVariable Long id) {
        billService.markAsPaid(id);
        return ResponseEntity.ok().build();
    }
}
