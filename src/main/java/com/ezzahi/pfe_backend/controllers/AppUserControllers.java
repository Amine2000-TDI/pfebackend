package com.ezzahi.pfe_backend.controllers;

import com.ezzahi.pfe_backend.dtos.*;
import com.ezzahi.pfe_backend.exceptions.OperationNonPermittedException;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.enums.EtatCompte;
import com.ezzahi.pfe_backend.services.AppUserService;
import com.ezzahi.pfe_backend.services.PreferenceService;
import com.ezzahi.pfe_backend.services.UserDetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class AppUserControllers {

    private final AppUserService appUserService;
    private final UserDetailService userDetailService;
    private final PreferenceService preferenceService;

    @PostMapping(value = {"/",""})
    @Transactional
    public ResponseEntity<AppUserDto> save(
            @RequestBody UserRegistrationRequest request
            ) {
        AppUserDto appUserDto = appUserService.save(request.getUser());
        Optional<UserDetailDto> existingUserDetail = userDetailService.getByAppUserId(appUserDto.getId());

        UserDetailDto detail = UserDetailDto.builder().phone(request.getPhone()).birthday(request.getBirthday()).dateVideoCall(request.getDateVideoCall()).appUser(appUserDto).build();

        //vérifier s'il y a un id pour détail ça veut dire c'est update
        // Mise à jour si un ID est fourni
        if (request.getId() != null) {
            existingUserDetail.ifPresentOrElse(existing -> {
                if (!request.getId().equals(existing.getId())) {
                    throw new OperationNonPermittedException("The user details do not belong to the correct user", "save appUser", "controller");
                }
            },() -> {
                userDetailService.delete(request.getId());
                throw new OperationNonPermittedException("The user details already register in the database but not belong to anyone", "save appUser", "controller");
            });

            detail.setId(request.getId());
        } else {
            // Sinon, assigner l’ID existant s’il existe
            existingUserDetail.ifPresent(existing -> detail.setId(existing.getId()));
        }

        userDetailService.save(detail);
        return ResponseEntity.ok(appUserDto);
    }

    @GetMapping(value = {"/",""})
    public ResponseEntity<List<AppUserDto>> getAllUsers() {
        return ResponseEntity.ok(appUserService.getAll());
    }


    @GetMapping("/role/{role}")
    public ResponseEntity<List<AppUserDto>> getAllRoles(
        @PathVariable("role")  String roleName
    ) {
        return ResponseEntity.ok(appUserService.getUsersByRole(roleName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDto> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(appUserService.getById(id));
    }

    @GetMapping("/etat/{etat}")
    public ResponseEntity<List<AppUserDto>> getUserByEtat(@PathVariable("etat") EtatCompte stat) {
        return ResponseEntity.ok(appUserService.getUsersByEtat(stat));
    }

    @PutMapping("/valid/{id}")
    public ResponseEntity<?> videoCallValidation(
            @PathVariable Long id) {
        LocalDate responseEntity = userDetailService.updateDateVideoCall(id);
        return ResponseEntity.ok(responseEntity);
    }

    @PutMapping("/chang-stat")
    public ResponseEntity<Void> changeEtat(
            @RequestBody StatUpdateRequest request
    ){
        appUserService.changeEtat(request.getId(),request.getEtat());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        appUserService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Preference

    @PostMapping("/preference")
    public ResponseEntity<PreferenceDto> savePreference(
            @RequestBody UserPreferenceRequest request
    ){
        AppUserDto user = appUserService.getById(request.getUser_id());
        PreferenceDto preferenceDto = PreferenceDto.builder()
                .user(user)
                .description(request.getDescription())
                .smoker(request.getSmoker())
                .dogLover(request.getDogLover())
                .catLover(request.getCatLover())
                .practicingSport(request.getPracticingSport())
                .practicingReligious(request.getPracticingReligious())
                .arabic(request.getArabic())
                .french(request.getFrench())
                .english(request.getEnglish())
                .spanish(request.getSpanish())
                .build();
        if(request.getId() != null) {
            preferenceDto.setId(request.getId());
        }
        preferenceService.save(preferenceDto);
        return ResponseEntity.ok(preferenceDto);
    }

}
