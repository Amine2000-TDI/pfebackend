package com.ezzahi.pfe_backend.services;

import com.ezzahi.pfe_backend.dtos.CommentDto;
import com.ezzahi.pfe_backend.models.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentService extends Crudservice<Comment, CommentDto> {
    List<CommentDto> getCommentsByTargetUser(Long userId);
    List<CommentDto> getCommentsByAuthorUser(Long userId);

}
