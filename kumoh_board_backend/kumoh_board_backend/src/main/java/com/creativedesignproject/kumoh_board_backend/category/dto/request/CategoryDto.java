package com.creativedesignproject.kumoh_board_backend.category.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class CategoryDto {
    private String name;

    @Builder
    public CategoryDto(String name) {
        this.name = name;
    }
}
