package com.creativedesignproject.kumoh_board_backend.Board.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;

import lombok.Getter;

@Getter
public class PutFavoriteResponseDto extends ResponseDto {
    private Integer favorite_count;

    private PutFavoriteResponseDto(Integer favorite_count) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.favorite_count = favorite_count;
    }

    public static ResponseEntity<PutFavoriteResponseDto> success(Integer favorite_count) {
        return ResponseEntity.status(HttpStatus.OK).body(new PutFavoriteResponseDto(favorite_count));
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER));
    }

    public static ResponseEntity<ResponseDto> notExistBoard() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD));
    }
}
