package com.creativedesignproject.kumoh_board_backend.board.dto.response;

import lombok.Data;

@Data
public class PutFavoriteResponseDto{
    private int favorite_count;

    public PutFavoriteResponseDto(int favorite_count) {
        this.favorite_count = favorite_count;
    }
}
