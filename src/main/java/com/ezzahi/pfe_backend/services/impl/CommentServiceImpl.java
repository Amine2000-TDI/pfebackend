package com.ezzahi.pfe_backend.services.impl;

import com.ezzahi.pfe_backend.dtos.CommentDto;
import com.ezzahi.pfe_backend.exceptions.NotFoundException;
import com.ezzahi.pfe_backend.models.Announcement;
import com.ezzahi.pfe_backend.models.AppUser;
import com.ezzahi.pfe_backend.models.Comment;
import com.ezzahi.pfe_backend.repositories.AnnouncementRepository;
import com.ezzahi.pfe_backend.repositories.AppUserRepository;
import com.ezzahi.pfe_backend.repositories.CommentRepository;
import com.ezzahi.pfe_backend.services.CommentService;
import com.ezzahi.pfe_backend.validators.ObjectValidators;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AppUserRepository appUserRepository;
    private final ObjectValidators<CommentDto> validator;

    @Override
    public CommentDto save(CommentDto dto) {
        validator.validate(dto);
        AppUser author = appUserRepository.findById(dto.getAuthor().getId())
                .orElseThrow(() -> new NotFoundException("Author not found with id : "+dto.getAuthor().getId(), "Comment Save"));
        AppUser target = appUserRepository.findById(dto.getTarget().getId())
                .orElseThrow(() -> new NotFoundException("Target not found with id : "+dto.getTarget().getId(), "Comment Save"));
        if(dto.getCreationDate() == null) {
            dto.setCreationDate(LocalDate.now());
        }else {
            Comment comment = commentRepository.findById(dto.getId())
                    .orElseThrow(() -> new NotFoundException("Comment not found with id : "+dto.getId(), "Comment Save"));
            comment.setCreationDate(comment.getCreationDate());
        }
        dto.setModificationDate(LocalDate.now());
        Comment comment = CommentDto.toEntity(dto, author, target);
        return CommentDto.toDto(commentRepository.save(comment));
    }

    @Override
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found with id : "+id, "Comment"));
        commentRepository.delete(comment);
    }

    @Override
    public CommentDto getById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found with id : "+id, "Comment"));
        return CommentDto.toDto(comment);
    }

    @Override
    public List<CommentDto> getAll() {
        return commentRepository.findAll().stream()
                .map(CommentDto::toDto)
                .toList();
    }
//*********************************************************************************
    public List<CommentDto> getCommentsByTargetUser(Long userId) {
        return commentRepository.findByTarget_Id(userId).stream()
                .map(CommentDto::toDto)
                .toList();
    }

    public List<CommentDto> getCommentsByAuthorUser(Long userId) {
        return commentRepository.findByAuthorId(userId).stream()
                .map(CommentDto::toDto)
                .toList();
    }

}
