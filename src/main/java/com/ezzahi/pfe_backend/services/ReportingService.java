package com.ezzahi.pfe_backend.services;

import com.ezzahi.pfe_backend.dtos.ReportingDto;
import com.ezzahi.pfe_backend.models.Reporting;
import com.ezzahi.pfe_backend.models.enums.ReportingStatus;

import java.util.List;

public interface ReportingService extends Crudservice<Reporting, ReportingDto> {
    List<ReportingDto> getReportsByTargetUser(Long userId);
    List<ReportingDto> getReportsByAuthor(Long authorId);
    ReportingDto updateStatus(Long id, ReportingStatus status);
    public long countReportsByUser(Long userId);
}
