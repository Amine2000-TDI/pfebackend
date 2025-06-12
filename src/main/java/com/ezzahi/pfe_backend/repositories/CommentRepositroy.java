package com.ezzahi.pfe_backend.repositories;

import com.ezzahi.pfe_backend.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepositroy extends JpaRepository<Comment,Long> {
}
