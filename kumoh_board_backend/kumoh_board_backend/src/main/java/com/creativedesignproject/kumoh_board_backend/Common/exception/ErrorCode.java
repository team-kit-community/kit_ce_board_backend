package com.creativedesignproject.kumoh_board_backend.Common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
    //400
    MISSING_NICKNAME(HttpStatus.BAD_REQUEST, "MISSING_NICKNAME", "닉네임을 입력해주세요."),
    EMAIL_DUPLICATED(HttpStatus.BAD_REQUEST, "EMAIL_DUPLICATED", "이메일이 이미 존재합니다."),
    EMAIL_SEND_FAIL(HttpStatus.BAD_REQUEST, "EMAIL_SEND_FAIL", "이메일 전송에 실패했습니다."),
    USER_ID_DUPLICATED(HttpStatus.BAD_REQUEST, "USER_ID_DUPLICATED", "아이디가 이미 존재합니다."),
    NICKNAME_DUPLICATED(HttpStatus.BAD_REQUEST, "NICKNAME_DUPLICATED", "닉네임이 이미 존재합니다."),
    CERTIFICATION_FAIL(HttpStatus.BAD_REQUEST, "CERTIFICATION_FAIL", "인증번호를 재전송 해주세요."),
    CERTIFICATION_MISSMATCHING(HttpStatus.BAD_REQUEST, "CERTIFICATION_MISSMATCHING", "인증번호가 일치하지 않습니다."),
    VALIDATION_FAIL(HttpStatus.BAD_REQUEST, "VALIDATION_FAIL", "검증이 실패하였습니다."),
    ;
    private HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
