package com.creativedesignproject.kumoh_board_backend.board.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PatchBoardRequestDto {
    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;
    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String content;
    private List<String> boardImageList;
}
