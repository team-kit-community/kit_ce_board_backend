package com.creativedesignproject.kumoh_board_backend.board.repository.query;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class CategoryPostDto {
    private String categoryName;
    private List<PostDto> postList;

    @Builder
    public CategoryPostDto(String categoryName, List<PostDto> postList) {
        this.categoryName = categoryName;
        this.postList = postList;
    }
}
