package com.ezzahi.pfe_backend.services;

import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Role;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import com.ezzahi.pfe_backend.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;

    public AppUserService(AppUserRepository appUserRepository, RoleRepository roleRepository){
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void addRolesToUser(String username, List<String> roleNames) {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        for (String roleName : roleNames) {
            Role role = roleRepository.findBylibelle(roleName).orElse(null);
            if (role != null && !user.getRoles().contains(role)) {
                user.getRoles().add(role);
            }
        }
        appUserRepository.save(user);
    }
}
