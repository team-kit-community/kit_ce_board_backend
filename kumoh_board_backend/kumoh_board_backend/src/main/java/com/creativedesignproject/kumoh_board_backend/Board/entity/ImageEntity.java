package com.creativedesignproject.kumoh_board_backend.Board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {
    private int image_id;
    private int post_number;
    private String image;
}
