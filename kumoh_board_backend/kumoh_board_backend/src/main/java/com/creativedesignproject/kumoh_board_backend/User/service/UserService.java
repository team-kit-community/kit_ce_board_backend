package com.creativedesignproject.kumoh_board_backend.user.service;

import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.user.dto.response.GetSignInUserResponseDto;

public interface UserService {
    ResponseEntity< ? super GetSignInUserResponseDto> getSignInUser(String userId);
    
}
