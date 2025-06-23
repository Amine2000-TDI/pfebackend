package com.ezzahi.pfe_backend.services.impl;

import com.ezzahi.pfe_backend.dtos.AppUserDto;
import com.ezzahi.pfe_backend.exceptions.NotFoundException;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Role;
import com.ezzahi.pfe_backend.models.enums.EtatCompte;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import com.ezzahi.pfe_backend.repositories.RoleRepository;
import com.ezzahi.pfe_backend.services.AppUserService;
import com.ezzahi.pfe_backend.validators.ObjectValidators;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final ObjectValidators<AppUserDto> validator;

    @Override
    public AppUserDto save(AppUserDto dto) {
        validator.validate(dto);
        List<Role> roles = dto.getRoles().stream()
                .map(roleDto -> roleRepository.findBylibelle(roleDto.getLibelle())
                        .orElseThrow(() -> new NotFoundException("Rôle non trouvé: " + roleDto.getLibelle(), "AppUser save")))
                .toList();

        AppUser user = AppUserDto.toEntity(dto);
        user.setRoles(roles);
        return AppUserDto.toDto(appUserRepository.save(user));
    }

    @Override
    public AppUserDto getById(Long id) {
        return appUserRepository.findById(id)
                .map(AppUserDto::toDto)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé avec l'ID: " + id, "AppUser"));
    }

    @Override
    public List<AppUserDto> getAll() {
        return appUserRepository.findAll().stream().map(AppUserDto::toDto).toList();
    }

    @Override
    public void delete(Long id) {
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé avec l'ID: " + id, "AppUser"));
        appUserRepository.delete(user);
    }

    @Override
    public AppUserDto update(Long id, AppUserDto dto) {
        validator.validate(dto);
        AppUser existing = appUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé", "AppUser update"));

        existing.setUsername(dto.getUsername());
        existing.setEmail(dto.getEmail());
        existing.setEtat(dto.getEtat());
        existing.setPhotoUrl(dto.getPhotoUrl());

        List<Role> roles = dto.getRoles().stream()
                .map(roleDto -> roleRepository.findBylibelle(roleDto.getLibelle())
                        .orElseThrow(() -> new NotFoundException("Rôle non trouvé: " + roleDto.getLibelle(), "AppUser update")))
                .toList();
        existing.setRoles(roles);

        return AppUserDto.toDto(appUserRepository.save(existing));
    }

    @Override
    public List<AppUserDto> getUsersByEtat(EtatCompte etat) {
        return appUserRepository.findByEtat(etat).stream().map(AppUserDto::toDto).toList();
    }

    @Override
    public List<AppUserDto> getUsersByRole(String roleName) {
        return appUserRepository.findAppUsersByRoleLibelle(roleName).stream()
                .map(AppUserDto::toDto)
                .toList();
    }

    @Override
    public AppUserDto changeEtat(Long id, EtatCompte etat) {
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé", "AppUser changeEtat"));
        user.setEtat(etat);
        return AppUserDto.toDto(appUserRepository.save(user));
    }
}
