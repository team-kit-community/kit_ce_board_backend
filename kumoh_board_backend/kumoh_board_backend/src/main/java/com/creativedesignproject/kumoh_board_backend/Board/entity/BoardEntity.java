package com.creativedesignproject.kumoh_board_backend.Board.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {
    private int post_number;
    private String title;
    private String contents;
    private Date write_datetime;
    private int favorite_count;
    private int comment_count;
    private int view_count;
    private int user_id;
    private int category_id;
    private List<ImageEntity> images;
    private String writeNickname;

    public void increaseCommentCount() {
        this.comment_count++;
    }

    public void increaseFavoriteCount() {
        this.favorite_count++;
    }

    public void decreaseFavoriteCount() {
        this.favorite_count--;
    }
}
