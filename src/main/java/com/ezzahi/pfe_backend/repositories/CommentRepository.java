package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByTarget_Id(Long targetUserId);
    List<Comment> findByAuthorId(Long authorId);
}
