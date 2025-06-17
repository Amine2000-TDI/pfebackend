package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Role;
import com.ezzahi.pfe_backend.models.enums.EtatCompte;
import com.ezzahi.pfe_backend.repositories.RoleRepository;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    @Size(min = 4, max = 50, message = "le username doit contenir entre 4 et 50")
    private String username;
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;
    @NotNull(message = "L'état du compte est obligatoire")
    private EtatCompte etat;
    // Peut-être nul, donc pas besoin de validation
    private String photoUrl;
    @NotEmpty(message = "L'utilisateur doit avoir au moins un rôle")
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
