package com.ezzahi.pfe_backend.services;

import com.ezzahi.pfe_backend.dtos.AppUserDto;
import com.ezzahi.pfe_backend.dtos.RoleDto;
import com.ezzahi.pfe_backend.models.Role;

import java.util.List;

public interface RoleService extends Crudservice<Role, RoleDto> {
    void assignRoleToUser(Long roleId, Long userId);
    void removeRoleFromUser(Long roleId, Long userId);
}
