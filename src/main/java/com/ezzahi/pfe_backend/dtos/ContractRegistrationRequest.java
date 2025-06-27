package com.ezzahi.pfe_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
public class ContractRegistrationRequest {
    private Long announcementId;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Long> participants;
}
