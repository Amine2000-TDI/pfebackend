package com.ezzahi.pfe_backend.services.impl;

import com.ezzahi.pfe_backend.dtos.AppUserDto;
import com.ezzahi.pfe_backend.dtos.RoleDto;
import com.ezzahi.pfe_backend.exceptions.DuplicateEntityException;
import com.ezzahi.pfe_backend.exceptions.NotFoundException;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Role;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import com.ezzahi.pfe_backend.repositories.RoleRepository;
import com.ezzahi.pfe_backend.services.RoleService;
import com.ezzahi.pfe_backend.validators.ObjectValidators;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final AppUserRepository appUserRepository;
    private final ObjectValidators<RoleDto> validator;

    @Override
    public RoleDto save(RoleDto dto) {
        validator.validate(dto);
        if(roleRepository.findBylibelle(dto.getLibelle()).isPresent()) {
            throw new DuplicateEntityException("Role withe libelle : "+dto.getLibelle()+" already exist","Role");
        }
        return RoleDto.toDto(roleRepository.save(RoleDto.toEntity(dto)));
    }

    @Override
    public void delete(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found with id: " + id,"Role"));
        List<AppUser> listAppUsers = appUserRepository.findByRolesLibelle(role.getLibelle());
        if(!listAppUsers.isEmpty()) {
            for (AppUser appUser : listAppUsers) {
                appUser.getRoles().remove(role);
                appUserRepository.save(appUser);
            }
        }
        roleRepository.delete(role);
    }

    @Override
    public RoleDto getById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found with id: " + id,"Role"));
        return RoleDto.toDto(role);
    }

    @Override
    public List<RoleDto> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(RoleDto::toDto)
                .toList();
    }
    //***************************************************************************************************************************

    @Override
    public void assignRoleToUser(Long roleId, Long userId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("Role not found with id: " + roleId, "Role Assign"));
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not foundwith id: " + userId, "Role Assign"));
        user.getRoles().add(role);
        appUserRepository.save(user);
    }

    @Override
    public void removeRoleFromUser(Long roleId, Long userId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("Role not found with id: " + roleId, "Role Assign"));
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not foundwith id: " + userId, "Role Assign"));
        user.getRoles().remove(role);
        appUserRepository.save(user);
    }

}
