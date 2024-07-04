package com.creativedesignproject.kumoh_board_backend.Auth.service;

import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.ChangeNicknameRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.ChangePasswordRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.EmailCertificationRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.SignInRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.SignUpRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ChangePasswordResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.SignInResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.UserIdCheckRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.UserIdCheckResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ChangeNicknameResponseDto;

public interface AuthService {
    void emailCertification(EmailCertificationRequestDto dto);
    void signUp(SignUpRequestDto dto);
    ResponseEntity<? super UserIdCheckResponseDto> checkUserId(UserIdCheckRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
    ResponseEntity<? super ChangePasswordResponseDto> changePassword(String userId, ChangePasswordRequestDto dto);
    ResponseEntity<? super ChangeNicknameResponseDto> changeNickname(String userId, ChangeNicknameRequestDto dto);
}
