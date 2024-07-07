package com.creativedesignproject.kumoh_board_backend.Auth.service;

import com.creativedesignproject.kumoh_board_backend.Auth.domain.VerificationCode;

public interface VerificationCodeProvider {
    VerificationCode provide();
}
