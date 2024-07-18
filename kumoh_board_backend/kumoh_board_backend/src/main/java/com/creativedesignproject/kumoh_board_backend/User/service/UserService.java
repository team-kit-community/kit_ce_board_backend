package com.creativedesignproject.kumoh_board_backend.user.service;

import com.creativedesignproject.kumoh_board_backend.user.dto.response.GetSignInUserResponseDto;

public interface UserService {
    GetSignInUserResponseDto getSignInUser(String userId);
    
}
