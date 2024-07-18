package com.creativedesignproject.kumoh_board_backend.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangePasswordRequestDto {
    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;
}
