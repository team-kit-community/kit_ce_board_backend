package com.creativedesignproject.kumoh_board_backend.board.domain.repository.query;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Data;

@Data
public class SubCommentDto {
    private String content;
    private UserDto userDto;

    @QueryProjection
    @Builder
    public SubCommentDto(String content, UserDto userDto) {
        this.content = content;
        this.userDto = userDto; 
    }
}
