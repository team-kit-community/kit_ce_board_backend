package com.creativedesignproject.kumoh_board_backend.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostCommentRequestDto {
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    private Integer subcomment_id;
}