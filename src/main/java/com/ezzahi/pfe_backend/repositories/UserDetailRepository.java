package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail,Long> {
}
