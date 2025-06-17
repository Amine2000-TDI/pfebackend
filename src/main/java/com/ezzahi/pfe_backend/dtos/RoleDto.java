package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {
    private Long id;
    @NotBlank(message = "La libelle de le role est obligatoire.")
    @Size(min = 2, max = 50, message = "la libelle doit être entre 3 et 50 caractère")
    private String libelle;

    public static RoleDto toDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .libelle(role.getLibelle())
                .build();
    }
    public static Role toEntity(RoleDto roleDto) {
        return Role.builder()
                .id(roleDto.getId())
                .libelle(roleDto.getLibelle())
                .build();
    }
}
