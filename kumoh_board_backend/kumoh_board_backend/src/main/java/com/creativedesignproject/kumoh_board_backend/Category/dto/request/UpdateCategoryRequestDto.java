package com.creativedesignproject.kumoh_board_backend.category.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class UpdateCategoryRequestDto {
    @NotNull(message = "카테고리 이름은 필수 입력 값입니다.")
    private String name;

    @Builder
    public UpdateCategoryRequestDto(String name) {
        this.name = name;
    }
}
