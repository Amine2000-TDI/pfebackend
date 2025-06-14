package com.ezzahi.pfe_backend;

import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.AnnouncementPicture;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Role;
import com.ezzahi.pfe_backend.models.enums.AnnonceType;
import com.ezzahi.pfe_backend.models.enums.EtatCompte;
import com.ezzahi.pfe_backend.models.enums.Status;
import com.ezzahi.pfe_backend.models.enums.TypeLogement;
import com.ezzahi.pfe_backend.repositories.AnnouncementPictureRepositroy;
import com.ezzahi.pfe_backend.repositories.AnnouncementRepositroy;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import com.ezzahi.pfe_backend.repositories.RoleRepository;
import com.ezzahi.pfe_backend.services.AnnouncementService;
import com.ezzahi.pfe_backend.services.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootApplication
public class PfeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PfeBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppUserRepository appUserRepository,
                                               RoleRepository roleRepository,
                                               AppUserService appUserService,
                                               AnnouncementService announcementService,
                                               AnnouncementRepositroy  announcementRepositroy,
                                               AnnouncementPictureRepositroy announcementPictureRepositroy
                                               ) {
        return args -> {
            AppUser user1 = appUserRepository.save(
                    AppUser.builder()
                            .username("amine")
                            .email("amine4@gmail.com")
                            .etat(EtatCompte.VALID)
                            .password("password")
                            .photoUrl("url")
                            .build());
            System.out.println("________________________________________________________");
            roleRepository.saveAll(List.of(
                    Role.builder()
                            .libelle("admin")
                            .build(),
                    Role.builder()
                            .libelle("coloc")
                            .build(),
                    Role.builder()
                            .libelle("propr")
                            .build()
                    )
            );
            System.out.println("_______________________________________________Add Role To APP USER_____________________________________________________");
            appUserService.addRolesToUser("amine",List.of("propr","admin"));
            appUserService.addRolesToUser("amine",List.of("propr","coloc"));
            appUserService.addRolesToUser("amine",List.of("propr","coloc","admin","agent"));
            System.out.println("_______________________________________________Add Announcement To APP USER_____________________________________________________");

            Announcement announcement = announcementRepositroy.save(
                    Announcement.builder()
                            .title("title 1")
                            .appUser(user1)
                            .description("description 1")
                            .price(100D)
                            .annonceType(AnnonceType.OWNER)
                            .adresse("adresse 1")
                            .typeLogement(TypeLogement.APARTMENT)
                            .nbrPerson(5)
                            .status(Status.AVAILABLE)
                            .build()
            );
            List<AnnouncementPicture> announcementPictureList =
            announcementPictureRepositroy.saveAll(List.of(
                    AnnouncementPicture.builder().url("url 1").build(),
                    AnnouncementPicture.builder().url("url 2").build(),
                    AnnouncementPicture.builder().url("url 3").build()
                    )
            );
            announcementService.addPicturesToAnnouncement(1L,announcementPictureList.stream()
                    .map(o -> o.getId()).collect(Collectors.toList())
            );

        };

    }

}
