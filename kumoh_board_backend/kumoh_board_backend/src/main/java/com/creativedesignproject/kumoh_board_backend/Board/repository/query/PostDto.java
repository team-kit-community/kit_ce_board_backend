package com.creativedesignproject.kumoh_board_backend.Board.repository.query;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto {
    private String title;
    private String contents;
    private int favoriteCount;
    private int commentCount;
    private int viewCount;
    private String userName;
    private String categoryName;
    private LocalDateTime updatedDate;

    public PostDto(String title, String contents, int favoriteCount, int commentCount, int viewCount, String userName, String categoryName, LocalDateTime updatedDate) {
        this.title = title;
        this.contents = contents;
        this.favoriteCount = favoriteCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
        this.userName = userName;
        this.categoryName = categoryName;
        this.updatedDate = updatedDate;
    }
}
