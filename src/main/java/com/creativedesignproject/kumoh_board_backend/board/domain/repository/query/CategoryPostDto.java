package com.creativedesignproject.kumoh_board_backend.board.domain.repository.query;

import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Data;

@Data
public class CategoryPostDto {
    private String categoryName;
    private List<PostDto> postList;

    @QueryProjection
    @Builder
    public CategoryPostDto(String categoryName, List<PostDto> postList) {
        this.categoryName = categoryName;
        this.postList = postList;
    }
}
