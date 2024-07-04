package com.creativedesignproject.kumoh_board_backend.Common.exception;

public class BadRequestException extends KitCeBoardException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
