package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Preference;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreferenceDto {
    private Long id;
    private Long userId;
    private Boolean smoker;
    private Boolean dogLover;
    private Boolean catLover;
    private Boolean practicinSport;
    private Boolean practicingReligious;
    private Boolean arabic;
    private Boolean french;
    private Boolean english;
    private Boolean spanich;

    public static PreferenceDto toDto(Preference preference) {
        return PreferenceDto.builder()
                .id(preference.getId())
                .userId(preference.getAppUser().getId())
                .smoker(preference.getSmoker())
                .dogLover(preference.getDogLover())
                .catLover(preference.getCatLover())
                .practicinSport(preference.getPracticinSport())
                .practicingReligious(preference.getPracticingReligious())
                .arabic(preference.getArabic())
                .french(preference.getFrench())
                .english(preference.getEnglish())
                .spanich(preference.getSpanich())
                .build();
    }
    public static Preference toEntity(PreferenceDto preferenceDto, AppUserRepository appUserRepository) {
        AppUser appUser = appUserRepository.findById(preferenceDto.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found with id +" + preferenceDto.getUserId()));
        return Preference.builder()
                .id(preferenceDto.getId())
                .appUser(appUser)
                .smoker(preferenceDto.getSmoker())
                .dogLover(preferenceDto.getDogLover())
                .catLover(preferenceDto.getCatLover())
                .practicinSport(preferenceDto.getPracticinSport())
                .arabic(preferenceDto.getArabic())
                .french(preferenceDto.getFrench())
                .english(preferenceDto.getEnglish())
                .spanich(preferenceDto.getSpanich())
                .build();
    }
}
