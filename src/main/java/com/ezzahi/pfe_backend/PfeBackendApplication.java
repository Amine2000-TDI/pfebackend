package com.ezzahi.pfe_backend;

import com.ezzahi.pfe_backend.repositories.AnnouncementPictureRepository;
import com.ezzahi.pfe_backend.repositories.AnnouncementRepository;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import com.ezzahi.pfe_backend.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PfeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PfeBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppUserRepository appUserRepository,
                                               RoleRepository roleRepository,
                                               AnnouncementRepository announcementRepository,
                                               AnnouncementPictureRepository announcementPictureRepositroy
                                               ) {
        return args -> {


        };

    }

}
