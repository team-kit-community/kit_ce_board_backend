package com.creativedesignproject.kumoh_board_backend.board.domain.repository.query;

import lombok.Data;

import com.querydsl.core.annotations.QueryProjection;

@Data
public class FavoriteListDto {
    private String userId;

    @QueryProjection
    public FavoriteListDto(String userId) {
        this.userId = userId;
    }
}
