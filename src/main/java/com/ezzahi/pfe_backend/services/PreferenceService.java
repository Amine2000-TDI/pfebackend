package com.ezzahi.pfe_backend.services;

import com.ezzahi.pfe_backend.dtos.PreferenceDto;
import com.ezzahi.pfe_backend.models.Preference;

public interface PreferenceService extends Crudservice<Preference, PreferenceDto> {
    PreferenceDto getByUserId(Long userId);
}
