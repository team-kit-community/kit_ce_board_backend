package com.creativedesignproject.kumoh_board_backend.board.domain.repository.query;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDto {
    private String title;
    private String contents;
    private int favoriteCount;
    private int commentCount;
    private int viewCount;
    private UserDto userDto;
    private CategoryPostDto categoryPostDto;
    private List<ImageDto> imageDto;
    private LocalDateTime updatedDate;

    @Builder
    public PostDto(String title, String contents, int favoriteCount, int commentCount, int viewCount, UserDto userDto, CategoryPostDto categoryPostDto, LocalDateTime updatedDate, List<ImageDto> imageDto) {
        this.title = title;
        this.contents = contents;
        this.favoriteCount = favoriteCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
        this.userDto = userDto;
        this.categoryPostDto = categoryPostDto;
        this.updatedDate = updatedDate;
    }
}