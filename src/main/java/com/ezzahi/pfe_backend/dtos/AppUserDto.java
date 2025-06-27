package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Role;
import com.ezzahi.pfe_backend.models.enums.EtatCompte;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
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
    private String password;
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;
    @NotNull(message = "L'état du compte est obligatoire")
    private EtatCompte etat;
    // Peut-être nul, donc pas besoin de validation
    private String photoUrl;
    @NotEmpty(message = "L'utilisateur doit avoir au moins un rôle")
    private List<RoleDto> roles;
    private LocalDate dateCreation;


    public static AppUserDto toDto(AppUser appUser) {
        return AppUserDto.builder()
                .id(appUser.getId())
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .email(appUser.getEmail())
                .etat(appUser.getEtat())
                .photoUrl(appUser.getPhotoUrl())
                .roles(appUser.getRoles().stream().map(RoleDto::toDto).toList())
                .dateCreation(appUser.getDateCreation())
                .build();
    }

    public static AppUser toEntity(AppUserDto appUserDto) {
        return AppUser.builder()
                .id(appUserDto.getId())
                .username(appUserDto.getUsername())
                .password(appUserDto.getPassword())
                .email(appUserDto.getEmail())
                .etat(appUserDto.getEtat())
                .photoUrl(appUserDto.getPhotoUrl())
                .roles(appUserDto.getRoles().stream().map(RoleDto::toEntity).toList())
                .dateCreation(appUserDto.getDateCreation())
                .build();
    }
}
