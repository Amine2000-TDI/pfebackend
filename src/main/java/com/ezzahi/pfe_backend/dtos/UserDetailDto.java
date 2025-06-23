package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.UserDetail;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
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
    private AppUserDto appUser;

    public static UserDetailDto toDto(UserDetail userDetail) {
        return UserDetailDto.builder()
                .id(userDetail.getId())
                .phone(userDetail.getPhone())
                .birthday(userDetail.getBirthday())
                .dateVideoCall(userDetail.getDateVideoCall())
                .appUser(AppUserDto.toDto(userDetail.getAppUser()))
                .build();
    }
    public static UserDetail toEntity(UserDetailDto userDetailDto, AppUser appUser) {
        return UserDetail.builder()
                .id(userDetailDto.getId())
                .phone(userDetailDto.getPhone())
                .birthday(userDetailDto.getBirthday())
                .dateVideoCall(userDetailDto.getDateVideoCall())
                .appUser(appUser)
                .build();
    }
}
