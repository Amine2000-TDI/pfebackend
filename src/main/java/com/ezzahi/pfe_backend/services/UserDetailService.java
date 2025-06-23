package com.ezzahi.pfe_backend.services;

import com.ezzahi.pfe_backend.dtos.UserDetailDto;
import com.ezzahi.pfe_backend.models.UserDetail;

import java.util.List;
import java.util.Optional;

public interface UserDetailService extends Crudservice<UserDetail, UserDetailDto> {
    Optional<UserDetailDto> getByAppUserId(Long appUserId);
    void updateDateVideoCall(Long userId);
    boolean hasDoneVideoCall(Long userId);
    List<UserDetailDto> findAllUserDetailWhoHaveNotDoneVideoCall();
}
