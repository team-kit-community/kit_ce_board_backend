package com.creativedesignproject.kumoh_board_backend.Auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creativedesignproject.kumoh_board_backend.Auth.service.AuthService;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.ChangeNicknameRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.ChangePasswordRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.EmailCertificationRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.SignInRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.SignUpRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.UserIdCheckRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.EmailCertificationResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.SignInResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.SignUpResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.UserIdCheckResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ChangePasswordResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ChangeNicknameResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/emailCertification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(
            @RequestBody @Valid EmailCertificationRequestDto requestBody) {
        return authService.emailCertification(requestBody);
    }

    @PostMapping("/checkUserId")
    public ResponseEntity<? super UserIdCheckResponseDto> checkUserId(
            @RequestBody @Valid UserIdCheckRequestDto requestBody) {
        return authService.checkUserId(requestBody);
    }

    @PostMapping("/signUp")
    public ResponseEntity<? super SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto requestBody) {
        return authService.signUp(requestBody);
    }

    @PostMapping("/signIn")
    public ResponseEntity<? super SignInResponseDto> signIn(@RequestBody @Valid SignInRequestDto requestBody) {
        return authService.signIn(requestBody);
    }

    @PatchMapping("/changePassword")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<? super ChangePasswordResponseDto> changePassword(@AuthenticationPrincipal String userId,
                                                              @RequestBody @Valid ChangePasswordRequestDto dto) {
        return authService.changePassword(userId, dto);
    }

    @PatchMapping("/changeNickname")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<? super ChangeNicknameResponseDto> changeNickname(@AuthenticationPrincipal String userId,
                                                              @RequestBody @Valid ChangeNicknameRequestDto dto) {
        return authService.changeNickname(userId, dto);
    }
}