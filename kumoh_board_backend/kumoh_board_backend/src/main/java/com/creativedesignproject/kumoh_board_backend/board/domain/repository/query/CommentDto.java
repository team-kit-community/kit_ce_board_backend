package com.creativedesignproject.kumoh_board_backend.board.domain.repository.query;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class CommentDto {
    private String contents;
    private UserDto userDto;
    private List<SubCommentDto> subComments;

    @Builder
    public CommentDto(String contents, UserDto userDto, List<SubCommentDto> subComments) {
        this.contents = contents;
        this.userDto = userDto;
        this.subComments = subComments;
    }
}
