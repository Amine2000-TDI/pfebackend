package com.ezzahi.pfe_backend.dtos;

import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Comment;
import com.ezzahi.pfe_backend.models.Contract;
import com.ezzahi.pfe_backend.models.Reporting;
import com.ezzahi.pfe_backend.models.enums.ReportingStatus;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private long id;
    private Long authorId;
    private Long targetId;
    private Contract contract;
    private String comment;
    private Date creationDate;
    private Date modificationDate;

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
