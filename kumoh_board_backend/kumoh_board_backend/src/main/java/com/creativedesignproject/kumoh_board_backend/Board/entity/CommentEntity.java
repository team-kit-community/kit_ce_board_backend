package com.creativedesignproject.kumoh_board_backend.Board.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    private int comment_id;
    private String contents;
    private Date write_datetime;
    private int subcomment_id;
    private int user_id;
    private int post_number;
    private String userNickname;
    private List<CommentEntity> subcomments = new ArrayList<>();
}
