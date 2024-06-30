package com.creativedesignproject.kumoh_board_backend.Board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteEntity {
    private int user_id;
    private int post_number;
}