package com.creativedesignproject.kumoh_board_backend.board.domain.repository.query;

import lombok.Builder;
import lombok.Data;

@Data
public class ImageDto {
    private String url;

    @Builder
    public ImageDto(String url) {
        this.url = url;
    }
}
