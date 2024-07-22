package com.creativedesignproject.kumoh_board_backend.category.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
public class CategoryDto {
    @NotEmpty(message = "카테고리 이름은 필수 입력 값입니다.")
    private String name;

    @Builder
    public CategoryDto(String name) {
        this.name = name;
    }
}
