package com.creativedesignproject.kumoh_board_backend.Category.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;

import lombok.Getter;

@Getter
public class RegisterCategoryResponseDto extends ResponseDto{
    private RegisterCategoryResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<RegisterCategoryResponseDto> success() {
        RegisterCategoryResponseDto responseBody = new RegisterCategoryResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> DuplicatedCategoryName() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_CATEGORY_NAME, ResponseMessage.DUPLICATE_CATEGORY_NAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
