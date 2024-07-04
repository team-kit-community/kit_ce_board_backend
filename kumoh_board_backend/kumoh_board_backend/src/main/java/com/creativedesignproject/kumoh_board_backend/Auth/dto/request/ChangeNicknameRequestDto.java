package com.creativedesignproject.kumoh_board_backend.Auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ChangeNicknameRequestDto {
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String newNickname;
}