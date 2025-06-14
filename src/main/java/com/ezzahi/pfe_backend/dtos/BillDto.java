package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.Bill;
import com.ezzahi.pfe_backend.models.ParticipatingContract;
import com.ezzahi.pfe_backend.models.enums.BillStatus;
import com.ezzahi.pfe_backend.models.enums.TypeOfCharge;
import com.ezzahi.pfe_backend.repositories.ParticipatingContractRepository;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDto {
    private long id;
    private Long participatingContractId;
    private TypeOfCharge typeOfCharge;
    private Double amount;
    private Date issueDate;
    private Date payementDate;
    private BillStatus billStatus;

    public static BillDto toDto(Bill bill) {
        return BillDto.builder()
                .id(bill.getId())
                .participatingContractId(bill.getParticipatingContract().getId())
                .typeOfCharge(bill.getTypeOfCharge())
                .amount(bill.getAmount())
                .issueDate(bill.getIssueDate())
                .payementDate(bill.getPayementDate())
                .billStatus(bill.getBillStatus())
                .build();
    }
    public static Bill toEntity(BillDto billDto, ParticipatingContractRepository participatingContractRepository) {
        ParticipatingContract participatingContract = participatingContractRepository.findById(billDto.getParticipatingContractId())
                .orElseThrow(() -> new RuntimeException("Participating Contract not found with id: " + billDto.getParticipatingContractId()));
        return Bill.builder()
                .id(billDto.getId())
                .participatingContract(participatingContract)
                .typeOfCharge(billDto.getTypeOfCharge())
                .amount(billDto.getAmount())
                .issueDate(billDto.getIssueDate())
                .payementDate(billDto.getPayementDate())
                .billStatus(billDto.getBillStatus())
                .build();
    }
}
