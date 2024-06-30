package com.creativedesignproject.kumoh_board_backend.Board.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.entity.BoardListEntity;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;

import lombok.Getter;

@Getter
public class GetUserBoardListResponseDto extends ResponseDto {
    List<BoardListEntity> boardListEntities = null;
    
    private GetUserBoardListResponseDto(List<BoardListEntity> boardListEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardListEntities = boardListEntities;
    }

    public static ResponseEntity<GetUserBoardListResponseDto> success(List<BoardListEntity> boardListEntities) {
        GetUserBoardListResponseDto responseBody = new GetUserBoardListResponseDto(boardListEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}