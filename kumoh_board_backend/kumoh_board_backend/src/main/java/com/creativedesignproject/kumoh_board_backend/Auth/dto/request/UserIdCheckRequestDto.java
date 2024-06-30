package com.creativedesignproject.kumoh_board_backend.Auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserIdCheckRequestDto {
    @NotBlank
    private String userId;
}
