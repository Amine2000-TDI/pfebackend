package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.UserDetail;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailDto {
    private Long id;
    private String phone;
    private Date birthday;
    private Date dateVideoCall;
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
