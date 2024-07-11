package com.creativedesignproject.kumoh_board_backend.auth.service;

import com.creativedesignproject.kumoh_board_backend.auth.domain.VerificationCode;

public interface VerificationCodeProvider {
    VerificationCode provide();
}
