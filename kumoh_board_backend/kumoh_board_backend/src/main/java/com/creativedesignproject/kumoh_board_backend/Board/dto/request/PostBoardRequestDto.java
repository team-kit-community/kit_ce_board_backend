package com.creativedesignproject.kumoh_board_backend.Board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostBoardRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private List<String> boardImageList;
}