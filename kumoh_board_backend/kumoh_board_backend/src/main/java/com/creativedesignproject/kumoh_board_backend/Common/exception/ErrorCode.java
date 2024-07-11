package com.creativedesignproject.kumoh_board_backend.common.exception;

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
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
    NOT_EXISTED_USER(HttpStatus.BAD_REQUEST, "NOT_EXISTED_USER", "존재하지 않는 사용자입니다."),
    PASSWORD_NOT_MATCHED(HttpStatus.BAD_REQUEST, "PASSWORD_NOT_MATCHED", "비밀번호가 일치하지 않습니다."),
    NOT_EXISTED_CATEGORY(HttpStatus.BAD_REQUEST, "NOT_EXISTED_CATEGORY", "존재하지 않는 카테고리입니다."),
    NOT_EXISTED_POST(HttpStatus.BAD_REQUEST, "NOT_EXISTED_POST", "존재하지 않는 게시글입니다."),
    NOT_EXISTED_COMMENT(HttpStatus.BAD_REQUEST, "NOT_EXISTED_COMMENT", "존재하지 않는 댓글입니다."),

    //401
    NO_PERMISSION(HttpStatus.UNAUTHORIZED, "NO_PERMISSION", "권한이 없습니다."),
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
