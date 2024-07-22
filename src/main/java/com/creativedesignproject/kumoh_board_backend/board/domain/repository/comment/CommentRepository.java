package com.creativedesignproject.kumoh_board_backend.board.domain.repository.comment;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creativedesignproject.kumoh_board_backend.board.domain.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>, QuerydslCommentRepository {
    Optional<Comment> findById(Long id);
}