package com.ezzahi.pfe_backend.services.impl;

import com.ezzahi.pfe_backend.dtos.PreferenceDto;
import com.ezzahi.pfe_backend.exceptions.NotFoundException;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Preference;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import com.ezzahi.pfe_backend.repositories.PreferenceRepository;
import com.ezzahi.pfe_backend.services.PreferenceService;
import com.ezzahi.pfe_backend.validators.ObjectValidators;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PreferenceServiceImpl implements PreferenceService {

    private final PreferenceRepository preferenceRepository;
    private final AppUserRepository appUserRepository;
    private final ObjectValidators<PreferenceDto> validator;

    @Override
    public PreferenceDto save(PreferenceDto dto) {
        validator.validate(dto);

        AppUser user = appUserRepository.findById(dto.getUser().getId())
                .orElseThrow(() -> new NotFoundException("User not found with id : "+dto.getUser().getId(), "Preference save"));

        // Si préférence déjà existante → update
        Preference preference = preferenceRepository.findByAppUserId(user.getId())
                .orElse(null);

        if (preference == null) {
            // Nouvelle préférence
            preference = PreferenceDto.toEntity(dto, user);
        } else {
            // Mise à jour
            preference.setDescription(dto.getDescription());
            preference.setSmoker(dto.getSmoker());
            preference.setDogLover(dto.getDogLover());
            preference.setCatLover(dto.getCatLover());
            preference.setPracticinSport(dto.getPracticinSport());
            preference.setPracticingReligious(dto.getPracticingReligious());
            preference.setArabic(dto.getArabic());
            preference.setFrench(dto.getFrench());
            preference.setEnglish(dto.getEnglish());
            preference.setSpanish(dto.getSpanish());
        }

        return PreferenceDto.toDto(preferenceRepository.save(preference));
    }


    @Override
    public PreferenceDto getById(Long id) {
        Preference preference = preferenceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Preference not found with id : "+id, "Preference getById"));
        return PreferenceDto.toDto(preference);
    }


    @Override
    public void delete(Long id) {
        Preference preference = preferenceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Preference not found with id : "+id, "Preference delete"));
        preferenceRepository.delete(preference);
    }

    @Override
    public List<PreferenceDto> getAll() {
        return preferenceRepository.findAll()
                .stream()
                .map(PreferenceDto::toDto)
                .toList();
    }

    @Override
    public PreferenceDto getByUserId(Long userId) {
        Preference preference = preferenceRepository.findByAppUserId(userId)
                .orElseThrow(() -> new NotFoundException("Preference not found for user that has id : "+userId, "Preference getByUserId"));
        return PreferenceDto.toDto(preference);
    }
}
