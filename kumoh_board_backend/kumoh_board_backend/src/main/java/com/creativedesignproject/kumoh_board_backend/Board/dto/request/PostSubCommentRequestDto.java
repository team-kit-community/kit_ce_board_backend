package com.creativedesignproject.kumoh_board_backend.Board.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostSubCommentRequestDto {
    private String content;
    private Integer parent_comment_id;
}