package com.creativedesignproject.kumoh_board_backend.Board.repository.query;

import java.util.List;

import lombok.Getter;

@Getter
public class CategoryPostDto {
    private String categoryName;
    private List<PostDto> postList;

    public CategoryPostDto(String categoryName, List<PostDto> postList) {
        this.categoryName = categoryName;
        this.postList = postList;
    }
}
