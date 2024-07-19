package com.creativedesignproject.kumoh_board_backend.auth.service;

import com.creativedesignproject.kumoh_board_backend.auth.domain.entity.VerificationCode;

public interface VerificationCodeProvider {
    VerificationCode provide();
}
