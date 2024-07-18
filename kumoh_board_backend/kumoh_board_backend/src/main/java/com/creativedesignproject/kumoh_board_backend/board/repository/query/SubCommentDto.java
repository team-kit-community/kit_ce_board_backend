package com.creativedesignproject.kumoh_board_backend.board.repository.query;

import lombok.Builder;
import lombok.Data;

@Data
public class SubCommentDto {
    private String content;
    private UserDto userDto;

    @Builder
    public SubCommentDto(String content, UserDto userDto) {
        this.content = content;
        this.userDto = userDto; 
    }
}
