package com.creativedesignproject.kumoh_board_backend.board.domain.repository.query;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Data;

@Data
public class ImageDto {
    private String url;

    @QueryProjection
    @Builder
    public ImageDto(String url) {
        this.url = url;
    }
}
