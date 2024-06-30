package com.creativedesignproject.kumoh_board_backend.Board.entity.primary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class FavoritePk implements Serializable {
    private static final long serialVersionUID = 1L;

    private int user_id;
    private int post_number;

    public FavoritePk(int user_id, int post_number) {
        this.user_id = Integer.valueOf(user_id);
        this.post_number = post_number;
    }
}

