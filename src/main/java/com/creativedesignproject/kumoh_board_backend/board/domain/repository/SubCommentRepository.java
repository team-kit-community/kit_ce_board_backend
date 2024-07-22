package com.creativedesignproject.kumoh_board_backend.board.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creativedesignproject.kumoh_board_backend.board.domain.entity.SubComment;

public interface SubCommentRepository extends JpaRepository<SubComment, Long> {
    List<SubComment> findByParentCommentId(Long parentCommentId);
}