package com.ezzahi.pfe_backend;

import com.ezzahi.pfe_backend.dtos.*;
import com.ezzahi.pfe_backend.exceptions.*;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.enums.*;
import com.ezzahi.pfe_backend.repositories.AnnouncementPictureRepository;
import com.ezzahi.pfe_backend.repositories.AnnouncementRepository;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import com.ezzahi.pfe_backend.repositories.RoleRepository;
import com.ezzahi.pfe_backend.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class PfeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PfeBackendApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(AppUserService appUserService,
                                               RoleService roleService,
                                               PreferenceService preferenceService,
                                               UserDetailService userDetailService,
                                               AnnouncementService announcementService,
                                               AnnouncementPictureService announcementPictureService,
                                               ContractService contractService
                                               ) {
        return args -> {
            System.out.println("********************************************************** affichage ***********************************************************************");
try {


            // Création des roles
            RoleDto roleAdmin = RoleDto.builder().libelle("Admin").build();
            RoleDto roleColoc = RoleDto.builder().libelle("Coloc").build();
            RoleDto rolePropr = RoleDto.builder().libelle("Propr").build();
            roleAdmin = roleService.save(roleAdmin);
            roleColoc = roleService.save(roleColoc);
            rolePropr = roleService.save(rolePropr);
            System.out.println("************** roles ******************");
            roleService.getAll().forEach(System.out::println);

            // Création des utilisateurs
            AppUserDto admin = AppUserDto.builder().username("admin").password("123456a").roles(List.of(roleAdmin)).photoUrl("line 1").email("admin@ezzahi.com").etat(EtatCompte.VALID).build();
            AppUserDto user1 = AppUserDto.builder().username("user1").password("123456a").roles(List.of(roleColoc)).photoUrl("line 2").email("user1@ezzahi.com").etat(EtatCompte.VALID).build();
            AppUserDto user2 = AppUserDto.builder().username("user2").password("123456a").roles(List.of(roleColoc, rolePropr)).photoUrl("line 3").email("user2@ezzahi.com").etat(EtatCompte.VALID).build();
            AppUserDto user3 = AppUserDto.builder().username("user3").password("123456a").roles(List.of(rolePropr)).photoUrl("line 4").email("user3@ezzahi.com").etat(EtatCompte.VALID).build();
            admin = appUserService.save(admin);
            user1 = appUserService.save(user1);
            user2 = appUserService.save(user2);
            user3 = appUserService.save(user3);
            System.out.println("************** users ******************");
            appUserService.getAll().forEach(System.out::println);



            // Préférences pour user1
            PreferenceDto preferenceUser1 = PreferenceDto.builder().user(user1).description("Je suis calme et sociable").smoker(false).dogLover(true).catLover(false).practicingSport(true).practicingReligious(false).arabic(true).french(true).english(true).spanish(false).build();
            PreferenceDto preferenceUser2 = PreferenceDto.builder().user(user2).description("Je suis calme et sociable et sportif").smoker(true).dogLover(false).catLover(true).practicingSport(false).practicingReligious(true).arabic(false).french(false).english(true).spanish(false).build();
            preferenceUser1 = preferenceService.save(preferenceUser1);
            preferenceUser2 = preferenceService.save(preferenceUser2);
            System.out.println("************** Preference ******************");
            preferenceService.getAll().forEach(System.out::println);



            // Détails utilisateur
            UserDetailDto user1Detail = UserDetailDto.builder().appUser(user1).gender(Gender.MAlE).phone("0612345678").birthday(LocalDate.of(2000, 5, 10)).dateVideoCall(LocalDate.of(2025, 7, 1)).build();
            user1Detail = userDetailService.save(user1Detail);
            System.out.println("************** UserDetail ******************");
            userDetailService.getAll().forEach(System.out::println);



            // Annonce && pictures
            AnnouncementPictureDto picture1 = AnnouncementPictureDto.builder().url("link_picture_1").build();
            AnnouncementPictureDto picture2 = AnnouncementPictureDto.builder().url("link_picture_2").build();
            AnnouncementPictureDto picture3 = AnnouncementPictureDto.builder().url("link_picture_3").build();
            AnnouncementPictureDto picture4 = AnnouncementPictureDto.builder().url("link_picture_4").build();
            AnnouncementDto announcement1 = AnnouncementDto.builder().ville(Ville.CASABLANCA).appUser(user2).title("title 2").description("Chambre calme, proche de tramway").adresse("adresse 1").price(2500.0).nbrPerson(4).surface(20).annonceType(AnnonceType.ROOMMATE).typeLogement(TypeLogement.ROOM).pictures(List.of( picture1,picture2,picture3,picture4)).build();
            AnnouncementDto announcement2 = AnnouncementDto.builder().ville(Ville.RABAT).appUser(user2).title("title 2").description("Chambre calme, proche de tramway").adresse("adresse 1").price(2500.0).nbrPerson(4).surface(20).annonceType(AnnonceType.ROOMMATE).typeLogement(TypeLogement.ROOM).pictures(List.of( picture1,picture2,picture3,picture4)).build();
            announcement1 = announcementService.save(announcement1);
            announcement2 = announcementService.save(announcement2);
            System.out.println("************** Annonce ******************");
            announcementService.getAll().forEach(System.out::println);


            // Contrat && participant
            
            ContractDto contract = ContractDto.builder().announcement(announcement1).participatingContracts(List.of()).startDate(LocalDate.of(2025, 7, 1)).endDate(LocalDate.of(2026, 7, 1)).build();
            System.out.println("************** Contract ******************");
            contract = contractService.save(contract);
            contractService.getAll().forEach(System.out::println);


}catch (OperationNonPermittedException e){
    System.out.println("************************************************* 1 *********************************************************");
    System.out.println(e.getMessage());
    System.out.println(e.getErrorMessage());
    System.out.println(e.getOperationId());
    System.out.println(e.getSource());
}catch (ObjectValidationException e){
    System.out.println("************************************************* 2 *********************************************************");
    e.getViolations().forEach(System.out::println);
    System.out.println(e.getViolationSource());
    System.out.println(e.getMessage());
}catch (DuplicateEntityException e){
    System.out.println("************************************************* 3 *********************************************************");
    System.out.println(e.getMessage());
    System.out.println(e.getSource());
} catch (NotFoundException e) {
    System.out.println(e.getMessage());
    System.out.println(e.getSource());
}
catch (InsufficientPhotosException e) {
    System.out.println("************************************************* 4 *********************************************************");
    System.out.println(e.getMessage());
} catch (Exception e) {
    System.out.println("************************************************* 5 *********************************************************");
    System.out.println(e.getMessage());
    System.out.println(e.getStackTrace());
}

            /*




// Participation au contrat
            ParticipatingContractDto pcUser1 = ParticipatingContractDto.builder()
                    .appUser(user1)
                    .contract(contract)
                    .participantType(ParticipantType.COLLOCATAIRE)
                    .entryDate(LocalDate.of(2025, 7, 1))
                    .exitDate(LocalDate.of(2026, 7, 1))
                    .build();

// Bill pour user1
            BillDto billUser1 = BillDto.builder()
                    .amount(1250.0)
                    .billStatus(BillStatus.NO_PAY)
                    .issueDate(LocalDate.of(2025, 7, 1))
                    .payementDate(null)
                    .participatingContractId(pcUser1.getId())
                    .build();

// Commentaire de user1 sur user2
            CommentDto comment = CommentDto.builder()
                    .author(user1)
                    .target(user2)
                    .content("Propriétaire très respectueux et réactif")
                    .creationDate(LocalDate.now())
                    .modificationDate(LocalDate.now())
                    .build();

// Signalement
            ReportingDto reporting = ReportingDto.builder()
                    .author(user1)
                    .target(user3)
                    .description("Comportement irrespectueux")
                    .status(ReportingStatus.EN_ATTENTE)
                    .reportingDate(LocalDate.now())
                    .build();


        */
        };

    }

}
