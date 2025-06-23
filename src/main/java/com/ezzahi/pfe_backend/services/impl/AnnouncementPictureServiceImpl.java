package com.ezzahi.pfe_backend.services.impl;

import com.ezzahi.pfe_backend.dtos.AnnouncementPictureDto;
import com.ezzahi.pfe_backend.exceptions.NotFoundException;
import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.AnnouncementPicture;
import com.ezzahi.pfe_backend.repositories.AnnouncementPictureRepository;
import com.ezzahi.pfe_backend.repositories.AnnouncementRepository;
import com.ezzahi.pfe_backend.services.AnnouncementPictureService;
import com.ezzahi.pfe_backend.validators.ObjectValidators;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class AnnouncementPictureServiceImpl implements AnnouncementPictureService {
    private final AnnouncementPictureRepository announcementPictureRepository;
    private final AnnouncementRepository announcementRepository;
    private final ObjectValidators<AnnouncementPictureDto> validator;

    @Override
    public AnnouncementPictureDto save(AnnouncementPictureDto dto) {
        validator.validate(dto);
        Announcement announcement = announcementRepository.findById(dto.getAnnouncementId())
                .orElseThrow(()-> new NotFoundException("Announcement not found with id : "+dto.getAnnouncementId()+"to save iamge","Announcement Picture"));

        return AnnouncementPictureDto.toDto(announcementPictureRepository.save(AnnouncementPictureDto.toEntity(dto, announcement)));
    }

    @Override
    public void delete(Long id) {
        AnnouncementPicture announcementPicture = announcementPictureRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Image not found with id : "+id,"Announcement Picture"));
        announcementPictureRepository.delete(announcementPicture);
    }

    @Override
    public AnnouncementPictureDto getById(Long id) {
        return AnnouncementPictureDto.toDto(announcementPictureRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Image not found with id : "+id,"Announcement Picture")));
    }

    @Override
    public List<AnnouncementPictureDto> getAll() {
        return announcementPictureRepository.findAll()
                .stream()
                .map(AnnouncementPictureDto::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<AnnouncementPictureDto> findAllByAnnouncementId(Long announcementId) {
        return announcementPictureRepository.findByAnnouncementId(announcementId)
                .stream()
                .map(AnnouncementPictureDto::toDto)
                .collect(Collectors.toList());
    }
    //******************************************************************************************************************************
    @Override
    public void deleteAllByAnnouncementId(Long announcementId) {
        List<AnnouncementPicture> pictures = announcementPictureRepository.findByAnnouncementId(announcementId);
        announcementPictureRepository.deleteAll(pictures);
    }
    @Override
    public AnnouncementPictureDto getMainPictureByAnnouncementId(Long announcementId) {
        return announcementPictureRepository.findFirstByAnnouncementId(announcementId)
                .map(AnnouncementPictureDto::toDto)
                .orElseThrow(() -> new NotFoundException("No main picture found", "Announcement Picture"));
    }
}
