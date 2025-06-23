package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.Bill;
import com.ezzahi.pfe_backend.models.ParticipatingContract;
import com.ezzahi.pfe_backend.models.enums.BillStatus;
import com.ezzahi.pfe_backend.models.enums.TypeOfCharge;
import com.ezzahi.pfe_backend.repositories.ParticipatingContractRepository;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDto {
    private long id;
    @NotNull(message = "Le participant est obligatoire")
    private ParticipatingContractDto participatingContract;
    @NotNull(message = "Le type de charge est obligatoire")
    private TypeOfCharge typeOfCharge;
    @NotNull(message = "Le montant est obligatoire")
    private Double amount;
    @NotNull(message = "La date d'émission est obligatoire")
    //@PastOrPresent(message = "La date d'émission ne peut pas être dans le futur")
    private LocalDate issueDate;
    // la date de payement doit en passé ou présent futur non
    private LocalDate paymentDate;
    @NotNull(message = "Le statut de la facture est obligatoire")
    private BillStatus billStatus;

    public static BillDto toDto(Bill bill) {
        return BillDto.builder()
                .id(bill.getId())
                .participatingContract(ParticipatingContractDto.toDto(bill.getParticipatingContract()))
                .typeOfCharge(bill.getTypeOfCharge())
                .amount(bill.getAmount())
                .issueDate(bill.getIssueDate())
                .paymentDate(bill.getPaymentDate())
                .billStatus(bill.getBillStatus())
                .build();
    }
    public static Bill toEntity(BillDto billDto, ParticipatingContract participatingContract) {
       return Bill.builder()
                .id(billDto.getId())
                .participatingContract(participatingContract)
                .typeOfCharge(billDto.getTypeOfCharge())
                .amount(billDto.getAmount())
                .issueDate(billDto.getIssueDate())
                .paymentDate(billDto.getPaymentDate())
                .billStatus(billDto.getBillStatus())
                .build();
    }
}
