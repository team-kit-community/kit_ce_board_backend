package com.creativedesignproject.kumoh_board_backend.board.domain.repository.comment;

import java.util.List;

import com.creativedesignproject.kumoh_board_backend.board.domain.entity.Comment;

public interface QuerydslCommentRepository {
    List<Comment> findByPostId(Long post_number);
}
