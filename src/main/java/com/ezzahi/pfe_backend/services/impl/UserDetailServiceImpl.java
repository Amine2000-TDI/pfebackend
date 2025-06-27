package com.ezzahi.pfe_backend.services.impl;


import com.ezzahi.pfe_backend.dtos.UserDetailDto;
import com.ezzahi.pfe_backend.exceptions.NotFoundException;
import com.ezzahi.pfe_backend.exceptions.OperationNonPermittedException;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.UserDetail;
import com.ezzahi.pfe_backend.models.enums.EtatCompte;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import com.ezzahi.pfe_backend.repositories.UserDetailRepository;
import com.ezzahi.pfe_backend.services.AppUserService;
import com.ezzahi.pfe_backend.services.UserDetailService;
import com.ezzahi.pfe_backend.validators.ObjectValidators;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {

    private final UserDetailRepository userDetailRepository;
    private final AppUserRepository appUserRepository;
    private final ObjectValidators<UserDetailDto> validator;
    private final AppUserService appUserService;

    @Override
    public UserDetailDto save(UserDetailDto dto) {
        validator.validate(dto);

        AppUser appUser = appUserRepository.findById(dto.getAppUser().getId())
                .orElseThrow(() -> new NotFoundException("User not found for details.", "UserDetail Save"));
        // si admin on doit pas ajouter Detail
        appUser.getRoles().forEach(role -> {
            if(role.getLibelle().equals("Admin")) {
                throw new OperationNonPermittedException("You can't add user details to an admin","save","UserDetail Save");
            }
        });
        if(!isEighteenOrOlder(dto.getBirthday())){
            throw new OperationNonPermittedException("You must have a 18 years old","save","UserDetail Save");
        }

        UserDetail userDetail = UserDetailDto.toEntity(dto, appUser);
        return UserDetailDto.toDto(userDetailRepository.save(userDetail));
    }

    @Override
    public void delete(Long id) {
        UserDetail userDetail = userDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("UserDetail not found with id : "+id, "UserDetail delete"));
        appUserService.delete(userDetail.getAppUser().getId());
    }

    @Override
    public UserDetailDto getById(Long id) {
        return userDetailRepository.findById(id)
                .map(UserDetailDto::toDto)
                .orElseThrow(() -> new NotFoundException("UserDetail not found with id : "+id, "UserDetail getbyID"));
    }

    @Override
    public List<UserDetailDto> getAll() {
        return userDetailRepository.findAll().stream()
                .map(UserDetailDto::toDto)
                .toList();
    }

    //*************************************************************************

    @Override
    public Optional<UserDetailDto> getByAppUserId(Long appUserId) {
        return userDetailRepository.findByAppUserId(appUserId)
                .map(UserDetailDto::toDto);
    }

    @Override
    public LocalDate updateDateVideoCall(Long userId) {
        UserDetail userDetail = userDetailRepository.findByAppUserId(userId)
                .orElseThrow(() -> new NotFoundException("UserDetail not found with id : "+userId, "UserDetail updateDateVideoCall"));
        userDetail.setDateVideoCall(LocalDate.now());
        userDetail = userDetailRepository.save(userDetail);
        appUserService.changeEtat(userId, EtatCompte.VALID);
        return userDetail.getDateVideoCall();
    }

    @Override
    public boolean hasDoneVideoCall(Long userId) {
        return userDetailRepository.findByAppUserId(userId)
                .map(detail -> detail.getDateVideoCall() != null)
                .orElse(false);
    }
    @Override
    public List<UserDetailDto> findAllUserDetailWhoHaveNotDoneVideoCall() {
        return userDetailRepository.findAll().stream()
                .filter(detail -> detail.getDateVideoCall() == null)
                .map(UserDetailDto::toDto)
                .toList();
    }


    //validation date for 18 years old
    public static boolean isEighteenOrOlder(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate eighteenYearsAgo = currentDate.minusYears(18);
        return birthDate.isBefore(eighteenYearsAgo) || birthDate.isEqual(eighteenYearsAgo);
    }


}