package com.creativedesignproject.kumoh_board_backend.Board.entity;

import java.util.Date;

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
public class BoardListEntity {
    private int post_number;
    private String title;
    private String contents;
    private String titleImage;
    private int comment_count;
    private int favorite_count;
    private int view_count;
    private int category_id;
    private String writeNickname;
    private Date writeDatetime;
    private String writeProfileImage;
    private String categoryName;
}
