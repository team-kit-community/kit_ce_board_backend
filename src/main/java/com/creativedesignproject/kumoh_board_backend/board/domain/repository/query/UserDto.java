package com.creativedesignproject.kumoh_board_backend.board.domain.repository.query;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Data;

@Data
public class UserDto {
    private String nickname;
    private String profile_image;

    @QueryProjection
    @Builder
    public UserDto(String nickname, String profile_image) {
        this.nickname = nickname;
        this.profile_image = profile_image;
    }
}
