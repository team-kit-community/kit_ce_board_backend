package com.creativedesignproject.kumoh_board_backend.category.domain.repository;

import java.util.List;

import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.CategoryPostDto;

public interface QuerydslCategoryRepository {
    List<CategoryPostDto> findAllCategoryPostDtos();
}
