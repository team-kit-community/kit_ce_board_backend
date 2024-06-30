package com.creativedesignproject.kumoh_board_backend.User.service;

import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.User.dto.response.GetSignInUserResponseDto;

public interface UserService {
    ResponseEntity< ? super GetSignInUserResponseDto> getSignInUser(String userId);
    
}
