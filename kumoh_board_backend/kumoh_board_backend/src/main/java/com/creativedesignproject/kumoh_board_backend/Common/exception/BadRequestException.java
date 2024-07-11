package com.creativedesignproject.kumoh_board_backend.common.exception;

public class BadRequestException extends KitCeBoardException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
