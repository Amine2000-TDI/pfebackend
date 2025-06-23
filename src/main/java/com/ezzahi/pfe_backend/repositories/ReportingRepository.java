package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.Reporting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportingRepository extends JpaRepository<Reporting,Long> {
    List<Reporting> findByTargetId(Long targetId);
    List<Reporting> findByAuthorId(Long authorId);
    long countByTargetId(Long targetId);
}
