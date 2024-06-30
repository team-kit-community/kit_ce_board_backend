package com.creativedesignproject.kumoh_board_backend.Board.entity;

import java.util.List;

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
public class CategoryBoardListEntity {
    private String categoryName;
    private List<BoardListEntity> boardListEntities;
}
