package com.creativedesignproject.kumoh_board_backend.board.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostSubCommentRequestDto {
    private String content;
    private Long parent_comment_id;
}