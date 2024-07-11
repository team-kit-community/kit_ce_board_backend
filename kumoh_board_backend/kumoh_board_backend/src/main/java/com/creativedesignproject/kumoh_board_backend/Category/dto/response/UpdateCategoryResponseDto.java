package com.creativedesignproject.kumoh_board_backend.category.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.common.ResponseMessage;
import com.creativedesignproject.kumoh_board_backend.auth.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class UpdateCategoryResponseDto extends ResponseDto {
    private UpdateCategoryResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<UpdateCategoryResponseDto> success() {
        UpdateCategoryResponseDto responseBody = new UpdateCategoryResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> DuplicatedCategoryName() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_CATEGORY_NAME,
                ResponseMessage.DUPLICATE_CATEGORY_NAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistedCategory() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_CATEGORY, ResponseMessage.NOT_EXISTED_CATEGORY);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
