package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Role;
import com.ezzahi.pfe_backend.models.enums.EtatCompte;
import com.ezzahi.pfe_backend.repositories.RoleRepository;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserDto {
    private Long id;
    private String username;
    private String email;
    private EtatCompte etat;
    private String photoUrl;
    private List<Long> roleId;


    public static AppUserDto toDto(AppUser appUser) {
        return AppUserDto.builder()
                .id(appUser.getId())
                .username(appUser.getUsername())
                .email(appUser.getEmail())
                .etat(appUser.getEtat())
                .photoUrl(appUser.getPhotoUrl())
                .roleId(appUser.getRoles().stream().map(Role::getId).collect(Collectors.toList()))
                .build();
    }

    public static AppUser toEntity(AppUserDto appUserDto, RoleRepository roleRepository) {
        List<Role> roles = appUserDto.getRoleId().stream()
                .map(roleId -> roleRepository.findById(roleId)
                        .orElseThrow(()-> new RuntimeException("Role not found with id : "+roleId)))
                .collect(Collectors.toList());
        return AppUser.builder()
                .id(appUserDto.getId())
                .username(appUserDto.getUsername())
                .email(appUserDto.getEmail())
                .etat(appUserDto.getEtat())
                .photoUrl(appUserDto.getPhotoUrl())
                .roles(roles)
                .build();
    }
}
