package com.creativedesignproject.kumoh_board_backend.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordRequestDto {
    @NotBlank(message = "기존 비밀번호는 필수 입력 값입니다.")
    private String oldPassword;

    @NotBlank(message = "새 비밀번호는 필수 입력 값입니다.")
    private String newPassword;
}