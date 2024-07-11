package com.creativedesignproject.kumoh_board_backend.auth.service;

import com.creativedesignproject.kumoh_board_backend.auth.dto.request.ChangeNicknameRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.request.ChangePasswordRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.request.EmailCertificationRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.request.SignInRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.request.SignUpRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.request.UserIdCheckRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.response.SignInResponseDto;

public interface AuthService {
    void emailCertification(EmailCertificationRequestDto dto);
    void signUp(SignUpRequestDto dto);
    void checkUserId(UserIdCheckRequestDto dto);
    SignInResponseDto signIn(SignInRequestDto dto);
    void changePassword(String userId, ChangePasswordRequestDto dto);
    void changeNickname(String userId, ChangeNicknameRequestDto dto);
}
