package com.creativedesignproject.kumoh_board_backend.user.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class GetSignInUserResponseDto{
    private String userId;
    private String nickName;
    private String profileImage;

    @Builder
    public GetSignInUserResponseDto(String userId, String nickName, String profileImage) {
        this.userId = userId;
        this.nickName = nickName;
        this.profileImage = profileImage;
    }
}
