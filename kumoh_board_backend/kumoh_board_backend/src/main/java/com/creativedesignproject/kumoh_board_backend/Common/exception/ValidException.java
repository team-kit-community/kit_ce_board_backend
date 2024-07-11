package com.creativedesignproject.kumoh_board_backend.common.exception;

public class ValidException extends KitCeBoardException {
    
    public ValidException(String message) {
        super(ErrorCode.VALIDATION_FAIL, message);
    }
}