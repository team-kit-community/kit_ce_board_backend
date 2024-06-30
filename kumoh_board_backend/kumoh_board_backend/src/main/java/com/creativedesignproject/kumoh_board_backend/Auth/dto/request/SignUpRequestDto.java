package com.creativedesignproject.kumoh_board_backend.Auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {
    @NotBlank
    private String userId;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[#@$!%\\?&])[A-Za-z0-9#@$!%\\?&]{8,13}$")
    private String password;

    @NotBlank
    private String nickName;

    @NotBlank
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9]+@kumoh\\.ac\\.kr$")
    private String email;

    @NotBlank
    private String certificationNumber;

    private String profileImage;
}
