package com.creativedesignproject.kumoh_board_backend.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creativedesignproject.kumoh_board_backend.auth.dto.request.ChangeNicknameRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.request.ChangePasswordRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.request.EmailCertificationRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.request.SignInRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.request.SignUpRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.request.UserIdCheckRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.response.SignInResponseDto;
import com.creativedesignproject.kumoh_board_backend.auth.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/emailCertification")
    public ResponseEntity<Void> emailCertification(@RequestBody @Valid EmailCertificationRequestDto requestBody) {
        authService.emailCertification(requestBody);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/checkUserId")
    public ResponseEntity<Void> checkUserId(
            @RequestBody @Valid UserIdCheckRequestDto requestBody) {
        authService.checkUserId(requestBody);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequestDto requestBody) {
        authService.signUp(requestBody);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody @Valid SignInRequestDto requestBody) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.signIn(requestBody));
    }

    @PatchMapping("/changePassword")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> changePassword(@AuthenticationPrincipal String userId,
                                                              @RequestBody @Valid ChangePasswordRequestDto dto) {
        authService.changePassword(userId, dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/changeNickname")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> changeNickname(@AuthenticationPrincipal String userId,
                                                              @RequestBody @Valid ChangeNicknameRequestDto dto) {
        authService.changeNickname(userId, dto);
        return ResponseEntity.ok().build();
    }
}