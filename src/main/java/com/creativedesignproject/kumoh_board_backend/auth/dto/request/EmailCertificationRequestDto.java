package com.creativedesignproject.kumoh_board_backend.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmailCertificationRequestDto {
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+@kumoh\\.ac\\.kr$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;
}