package com.creativedesignproject.kumoh_board_backend.Auth.service;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.ChangeNicknameRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.ChangePasswordRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.EmailCertificationRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.SignInRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.SignUpRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.SignInResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.UserIdCheckRequestDto;

public interface AuthService {
    void emailCertification(EmailCertificationRequestDto dto);
    void signUp(SignUpRequestDto dto);
    void checkUserId(UserIdCheckRequestDto dto);
    SignInResponseDto signIn(SignInRequestDto dto);
    void changePassword(String userId, ChangePasswordRequestDto dto);
    void changeNickname(String userId, ChangeNicknameRequestDto dto);
}
