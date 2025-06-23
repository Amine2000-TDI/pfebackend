package com.ezzahi.pfe_backend.services.impl;

import com.ezzahi.pfe_backend.dtos.BillDto;
import com.ezzahi.pfe_backend.exceptions.NotFoundException;
import com.ezzahi.pfe_backend.models.Bill;
import com.ezzahi.pfe_backend.models.ParticipatingContract;
import com.ezzahi.pfe_backend.models.enums.BillStatus;
import com.ezzahi.pfe_backend.repositories.BillRepository;
import com.ezzahi.pfe_backend.repositories.ParticipatingContractRepository;
import com.ezzahi.pfe_backend.services.BillService;
import com.ezzahi.pfe_backend.validators.ObjectValidators;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final ObjectValidators<BillDto> validator;
    private final ParticipatingContractRepository participatingContractRepository;

    @Override
    public BillDto save(BillDto dto) {
        validator.validate(dto);
        if (dto.getIssueDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La date d'émission ne peut pas être dans le futur.");
        }
        if (dto.getPaymentDate() != null && dto.getPaymentDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La date de paiement ne peut pas être dans le futur.");
        }
        ParticipatingContract participatingContract = participatingContractRepository.findById(dto.getParticipatingContract().getId())
                .orElseThrow(()-> new NotFoundException("ParticipatingContract not foud with id : "+dto.getParticipatingContract().getId(),"Bill save"));
        Bill bill = BillDto.toEntity(dto,participatingContract);
        return BillDto.toDto(billRepository.save(bill));
    }

    @Override
    public void delete(Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bill not found with id: " + id, "Bill delete"));
        billRepository.delete(bill);
    }

    @Override
    public BillDto getById(Long id) {
        return billRepository.findById(id)
                .map(BillDto::toDto)
                .orElseThrow(() -> new NotFoundException("Bill not found with id: " + id, "Bill getbyid"));
    }

    @Override
    public List<BillDto> getAll() {
        return billRepository.findAll()
                .stream()
                .map(BillDto::toDto)
                .toList();
    }
    //*********************************************************************************

    @Override
    public List<BillDto> getByUserId(Long userId) {
        return billRepository.findByParticipatingContract_AppUser_Id(userId)
                .stream().map(BillDto::toDto).toList();
    }

    @Override
    public List<BillDto> getByColocationId(Long colocationId) {
        return billRepository.findByParticipatingContract_Contract_Id(colocationId)
                .stream().map(BillDto::toDto).toList();
    }

    @Override
    public List<BillDto> getUnpaidBillsByUser(Long userId) {
        return billRepository.findByParticipatingContract_AppUser_IdAndIsPaidFalse(userId)
                .stream().map(BillDto::toDto).toList();
    }

    @Override
    public BillDto markAsPaid(Long billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new NotFoundException("Bill not found with id: " + billId, "Bill getbyid"));
        bill.setBillStatus(BillStatus.PAY);
        return BillDto.toDto(billRepository.save(bill));
    }

    @Override
    public Double getTotalAmountByUser(Long userId) {
        return billRepository.getTotalAmountByUser(userId);
    }
}
