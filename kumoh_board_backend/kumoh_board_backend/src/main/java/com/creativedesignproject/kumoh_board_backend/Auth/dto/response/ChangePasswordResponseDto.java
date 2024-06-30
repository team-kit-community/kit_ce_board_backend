package com.creativedesignproject.kumoh_board_backend.Auth.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;

import lombok.Getter;

@Getter
public class ChangePasswordResponseDto extends ResponseDto {
    private ChangePasswordResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<ChangePasswordResponseDto> success() {
        ChangePasswordResponseDto responseBody = new ChangePasswordResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicatePassword() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_PASSWORD, ResponseMessage.DUPLICATE_PASSWORD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}