package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.enums.EtatCompte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    // Trouver un utilisateur par son email (utile pour l'authentification ou vérification)
    Optional<AppUser> findByEmail(String email);

    // Trouver un utilisateur par son nom d'utilisateur (optionnel)
    Optional<AppUser> findByUsername(String username);

    // Lister tous les utilisateurs par état (ACTIF, BLOQUE, etc.)
    List<AppUser> findByEtat(EtatCompte etat);

    // Lister tous les utilisateurs ayant un rôle spécifique (par libellé)
    List<AppUser> findByRolesLibelle(String libelle);

    // Vérifier l'existence d'un utilisateur par email (utile pour signup)
    boolean existsByEmail(String email);

    // Compter le nombre d’utilisateurs par rôle (utile pour stats)
    long countByRolesLibelle(String libelle);

}
