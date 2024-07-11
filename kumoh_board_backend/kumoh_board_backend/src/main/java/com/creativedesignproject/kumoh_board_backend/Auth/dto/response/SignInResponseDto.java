package com.creativedesignproject.kumoh_board_backend.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInResponseDto{
    private String token;
    private int expirationTime; // 토큰 만료시간

    @Builder
    public SignInResponseDto(String token) {
        this.token = token;
        this.expirationTime = 3600;
    }
}
