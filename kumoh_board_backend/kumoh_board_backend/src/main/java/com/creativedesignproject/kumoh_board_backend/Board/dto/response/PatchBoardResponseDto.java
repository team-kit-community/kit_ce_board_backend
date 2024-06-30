package com.creativedesignproject.kumoh_board_backend.Board.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;

import lombok.Getter;

@Getter
public class PatchBoardResponseDto extends ResponseDto{
    private PatchBoardResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PatchBoardResponseDto> success() {
        return ResponseEntity.status(HttpStatus.OK).body(new PatchBoardResponseDto());
    }

    public static ResponseEntity<ResponseDto> notExistedCategory() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_CATEGORY,
                ResponseMessage.NOT_EXISTED_CATEGORY);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER));
    }

    public static ResponseEntity<ResponseDto> notExistBoard() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD));
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION));
    }
}
