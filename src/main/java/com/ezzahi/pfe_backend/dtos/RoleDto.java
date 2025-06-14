package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.Role;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {
    private Long id;
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
