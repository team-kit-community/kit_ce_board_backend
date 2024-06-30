package com.creativedesignproject.kumoh_board_backend.Category.entity;

import com.creativedesignproject.kumoh_board_backend.Category.common.SortStatus;

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
public class CategoryEntity {
    private int category_id;
    private String name;
    private SortStatus sortStatus;
    private int searchCount;
    private int pagingStartOffset;
}