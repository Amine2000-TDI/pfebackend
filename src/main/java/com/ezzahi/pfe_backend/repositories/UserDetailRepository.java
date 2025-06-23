package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail,Long> {
    Optional<UserDetail> findByAppUserId(Long appUserId);
}
