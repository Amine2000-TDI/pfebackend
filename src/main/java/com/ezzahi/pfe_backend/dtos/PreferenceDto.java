package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Preference;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreferenceDto {
    private Long id;
    @NotNull(message = "L'utilisateur est obligatoire")
    private Long userId;
    @NotBlank(message = "La description ne peut pas être vide")
    @Size(min = 2, max = 250, message = "La description doit contenir entre 2 et 250 caractères")
    private String description;
    @NotNull(message = "Le champ 'fumeur' est requis")
    private Boolean smoker;
    @NotNull(message = "Le champ 'aime les chiens' est requis")
    private Boolean dogLover;
    @NotNull(message = "Le champ 'aime les chats' est requis")
    private Boolean catLover;
    @NotNull(message = "Le champ 'sportif' est requis")
    private Boolean practicinSport;
    @NotNull(message = "Le champ 'pratiquant religieux' est requis")
    private Boolean practicingReligious;
    @NotNull(message = "La préférence de langue arabe est requise")
    private Boolean arabic;
    @NotNull(message = "La préférence de langue française est requise")
    private Boolean french;
    @NotNull(message = "La préférence de langue anglaise est requise")
    private Boolean english;
    @NotNull(message = "La préférence de langue espagnole est requise")
    private Boolean spanish;

    public static PreferenceDto toDto(Preference preference) {
        return PreferenceDto.builder()
                .id(preference.getId())
                .userId(preference.getAppUser().getId())
                .description(preference.getDescription())
                .smoker(preference.getSmoker())
                .dogLover(preference.getDogLover())
                .catLover(preference.getCatLover())
                .practicinSport(preference.getPracticinSport())
                .practicingReligious(preference.getPracticingReligious())
                .arabic(preference.getArabic())
                .french(preference.getFrench())
                .english(preference.getEnglish())
                .spanish(preference.getSpanish())
                .build();
    }
    public static Preference toEntity(PreferenceDto preferenceDto, AppUserRepository appUserRepository) {
        AppUser appUser = appUserRepository.findById(preferenceDto.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found with id +" + preferenceDto.getUserId()));
        return Preference.builder()
                .id(preferenceDto.getId())
                .appUser(appUser)
                .description(preferenceDto.getDescription())
                .smoker(preferenceDto.getSmoker())
                .dogLover(preferenceDto.getDogLover())
                .catLover(preferenceDto.getCatLover())
                .practicinSport(preferenceDto.getPracticinSport())
                .arabic(preferenceDto.getArabic())
                .french(preferenceDto.getFrench())
                .english(preferenceDto.getEnglish())
                .spanish(preferenceDto.getSpanish())
                .build();
    }
}
