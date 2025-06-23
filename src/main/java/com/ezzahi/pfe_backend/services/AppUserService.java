package com.ezzahi.pfe_backend.services;

import com.ezzahi.pfe_backend.dtos.AppUserDto;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.enums.EtatCompte;

import java.util.List;

public interface AppUserService extends Crudservice<AppUser, AppUserDto> {
    public AppUserDto update(Long id, AppUserDto dto);
    List<AppUserDto> getUsersByEtat(EtatCompte etat);
    List<AppUserDto> getUsersByRole(String roleName);
    AppUserDto changeEtat(Long id, EtatCompte etat);
}
