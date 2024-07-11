package com.creativedesignproject.kumoh_board_backend.category.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.common.ResponseMessage;

import lombok.Getter;

@Getter
public class DeleteCategoryResponseDto extends ResponseDto{
    private DeleteCategoryResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<DeleteCategoryResponseDto> success() {
        DeleteCategoryResponseDto responseBody = new DeleteCategoryResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistedCategory() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_CATEGORY, ResponseMessage.NOT_EXISTED_CATEGORY);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
