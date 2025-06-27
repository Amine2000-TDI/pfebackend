package com.ezzahi.pfe_backend.services.impl;

import com.ezzahi.pfe_backend.dtos.AnnouncementDto;
import com.ezzahi.pfe_backend.dtos.AppUserDto;
import com.ezzahi.pfe_backend.exceptions.InsufficientPhotosException;
import com.ezzahi.pfe_backend.exceptions.NotFoundException;
import com.ezzahi.pfe_backend.exceptions.OperationNonPermittedException;
import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.enums.Status;
import com.ezzahi.pfe_backend.repositories.AnnouncementRepository;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import com.ezzahi.pfe_backend.services.AnnouncementService;
import com.ezzahi.pfe_backend.services.AppUserService;
import com.ezzahi.pfe_backend.validators.ObjectValidators;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final ObjectValidators<AnnouncementDto> validators;
    private final AppUserRepository appUserRepository;

    @Override
    public AnnouncementDto save(AnnouncementDto dto) {
        validators.validate(dto);
        System.out.println("*********************************************start*************************************************");
        //verifier si une modification chercher l'annonce s'il exist ou non
        if(dto.getId() != null){
            Announcement announcement = announcementRepository.findById(dto.getId())
                    .orElseThrow(()-> new NotFoundException("An announcement with id " + dto.getId()  + " not found.","Announcement"));
            dto.setCreationDate(announcement.getCreationDate());
            announcement.getPictures().clear();
        }else{
            dto.setCreationDate(LocalDate.now());
            dto.setStatus(Status.AVAILABLE);
        }
        //verifier s'il y a au minimum 4 images
        if(dto.getPictures().isEmpty() || dto.getPictures().size() <= 3){
            throw new InsufficientPhotosException("An announcement must have at least 4 photos.");
        }
        //préparer le dto
        Announcement announcement = AnnouncementDto.toEntity(dto,AppUserDto.toEntity(dto.getAppUser()));
        announcement.getPictures().forEach( p -> p.setAnnouncement(announcement));
        //enregistrer l'annonce
        return AnnouncementDto.toDto(announcementRepository.save(AnnouncementDto.toEntity(dto,AppUserDto.toEntity(dto.getAppUser()))));
    }

    @Override
    public void delete(Long id) {
        //find announce
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("An announcement with id " + id + " not found.","Announcement"));
        announcementRepository.delete(announcement);
    }

    @Override
    public AnnouncementDto getById(Long id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("An announcement with id " + id + " not found.","Announcement"));
        return AnnouncementDto.toDto(announcement);
    }

    @Override
    public List<AnnouncementDto> getAll() {
        return announcementRepository.findAll().stream().map(AnnouncementDto::toDto).toList();
    }

    public List<AnnouncementDto> getByUserId(Long userId) {
        return announcementRepository.findByAppUserId(userId)
                .stream()
                .map(AnnouncementDto::toDto)
                .toList();
    }


    //****************************************************************************************************************************
    @Override
    public List<AnnouncementDto> getByStatus(Status status) {
        return announcementRepository.findByStatus(status)
                .stream()
                .map(AnnouncementDto::toDto)
                .toList();
    }

    @Override
    public List<AnnouncementDto> searchByKeyword(String keyword){
        return announcementRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword)
                .stream()
                .map(AnnouncementDto::toDto)
                .toList();
    }
    @Override
    public List<AnnouncementDto> getRecentAnnouncements() {
        return announcementRepository.findTop10ByOrderByCreationDateDesc()
                .stream()
                .map(AnnouncementDto::toDto)
                .toList();
    }

    @Override
    public void changeStatus(Long id, Status status){
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("An announcement with id " + id + " not found.","Announcement"));
        if(status.equals(Status.SUSPENDED)){
            throw new OperationNonPermittedException("You can't suspend an announcement","changeStatus","Announcement service");
        }
        announcement.setStatus(status);
        announcementRepository.save(announcement);
    }

    @Override
    public void suspendAnnouncement(Long id){
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("An announcement with id " + id + " not found.","Announcement"));
        announcement.setStatus(Status.SUSPENDED);
        announcementRepository.save(announcement);
    }

}
