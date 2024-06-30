package com.creativedesignproject.kumoh_board_backend.User.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.creativedesignproject.kumoh_board_backend.User.dto.response.GetSignInUserResponseDto;
import com.creativedesignproject.kumoh_board_backend.User.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    @GetMapping("")
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(@AuthenticationPrincipal String userId) {
        return userService.getSignInUser(userId);
    }
}
