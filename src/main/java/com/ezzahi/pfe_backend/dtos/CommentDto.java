package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Comment;
import com.ezzahi.pfe_backend.models.Contract;
import com.ezzahi.pfe_backend.models.Reporting;
import com.ezzahi.pfe_backend.models.enums.ReportingStatus;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private long id;
    @NotNull(message = "L'auteur est requis")
    private Long authorId;
    @NotNull(message = "La cible est requise")
    private Long targetId;
    @NotNull(message = "Le contrat est obligatoire")
    private Contract contract;
    @NotBlank(message = "Le commentaire  ne peut pas être vide")
    @Size(min = 2, max = 350, message = "Le commentaire doit contenir entre 2 et 350 caractères")
    private String comment;
    //couche service
    private LocalDate creationDate;
    //couche service
    private LocalDate modificationDate;

    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .authorId(comment.getAuthor().getId())
                .targetId(comment.getTarget().getId())
                .comment(comment.getComment())
                .creationDate(comment.getCreationDate())
                .modificationDate(comment.getModificationDate())
                .build();
    }
    public static Comment toEntity(CommentDto commentDto, AppUserRepository appUserRepository) {
        AppUser authorUser = appUserRepository.findById(commentDto.getAuthorId())
                .orElseThrow(()-> new RuntimeException("User not found with id +" + commentDto.getAuthorId()));
        AppUser authorTarget = appUserRepository.findById(commentDto.getTargetId())
                .orElseThrow(()-> new RuntimeException("User not found with id +" + commentDto.getTargetId()));
        return Comment.builder()
                .id(commentDto.getId())
                .author(authorUser)
                .target(authorTarget)
                .comment(commentDto.getComment())
                .creationDate(commentDto.getCreationDate())
                .modificationDate(commentDto.getModificationDate())
                .build();

    }
}
