package com.ezzahi.pfe_backend.controllers;

import com.ezzahi.pfe_backend.dtos.*;
import com.ezzahi.pfe_backend.services.AnnouncementPictureService;
import com.ezzahi.pfe_backend.services.AnnouncementService;
import com.ezzahi.pfe_backend.services.AppUserService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("announcements")
public class AnnouncementController {
    private final AnnouncementService announcementService;
    private final AnnouncementPictureService announcementPictureService;
    private final AppUserService appUserService;

    @PostMapping(value = {"/",""})
    @Transactional
    public ResponseEntity<AnnouncementDto> save(
            @RequestBody AnnouncementRegistrationRequest request
    ){
        AppUserDto user = appUserService.getById(request.getUser_id());
        List<AnnouncementPictureDto> pictures = request.getPictures();
        AnnouncementDto announcementDto = AnnouncementDto.builder()
                .id(request.getId())
                .appUser(user)
                .pictures(pictures)
                .title(request.getTitle())
                .description(request.getDescription())
                .adresse(request.getAdresse())
                .price(request.getPrice())
                .nbrPerson(request.getNbrPerson())
                .surface(request.getSurface())
                .annonceType(request.getAnnonceType())
                .typeLogement(request.getTypeLogement())
                .ville(request.getVille())
                .build();
        AnnouncementDto announcement = announcementService.save(announcementDto);
        return ResponseEntity.ok(announcement);
    }

    @GetMapping(value = {"/",""})
    public ResponseEntity<List<AnnouncementDto>> getAll(){
        return ResponseEntity.ok(announcementService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(announcementService.getById(id));
    }

    // les annonces d'un utilisateur
    @GetMapping("/user/{id}")
    public ResponseEntity<List<AnnouncementDto>> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(announcementService.getByUserId(id));
    }

    @PutMapping("/change-status")
    public ResponseEntity<Void> changeStatus(
            @RequestBody AnnouncementStatusUpdate request
    ){
        announcementService.changeStatus(request.getId(), request.getStatus());
        return ResponseEntity.ok().build();
    }

    //only admin
    @PutMapping("/suspend/{id}")
    public ResponseEntity<Void> suspendAnnouncement(
            @PathVariable Long id
    ){
        announcementService.suspendAnnouncement(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAnnouncementId(@PathVariable Long id) {
        announcementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
