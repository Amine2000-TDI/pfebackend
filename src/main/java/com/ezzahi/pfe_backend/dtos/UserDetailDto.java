package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.UserDetail;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailDto {
    private Long id;
    @NotBlank(message = "Le numéro de téléphone est obligatoire.")
    @Pattern(
            regexp = "^(\\+212|0)(6|7)[0-9]{8}$",
            message = "Le numéro doit être marocain et contenir 10 chiffres, ex : +2126XXXXXXXX ou 06XXXXXXXX."
    )
    private String phone;
    @NotNull(message = "La date de naissance est obligatoire.")
    private LocalDate birthday;
    private LocalDate dateVideoCall;
    @NotNull(message = "L'identifiant de l'utilisateur est obligatoire.")
    private Long appUserId;

    public static UserDetailDto toDto(UserDetail userDetail) {
        return UserDetailDto.builder()
                .id(userDetail.getId())
                .phone(userDetail.getPhone())
                .birthday(userDetail.getBirthday())
                .dateVideoCall(userDetail.getDateVideoCall())
                .appUserId(userDetail.getAppUser().getId())
                .build();
    }
    public static UserDetail toEntity(UserDetailDto userDetailDto, AppUserRepository appUserRepository) {
        AppUser appUser = appUserRepository.findById(userDetailDto.appUserId)
            .orElseThrow(()-> new RuntimeException("User not found with id " + userDetailDto.appUserId));
        return UserDetail.builder()
                .id(userDetailDto.getId())
                .phone(userDetailDto.getPhone())
                .birthday(userDetailDto.getBirthday())
                .dateVideoCall(userDetailDto.getDateVideoCall())
                .appUser(appUser)
                .build();
    }
}
