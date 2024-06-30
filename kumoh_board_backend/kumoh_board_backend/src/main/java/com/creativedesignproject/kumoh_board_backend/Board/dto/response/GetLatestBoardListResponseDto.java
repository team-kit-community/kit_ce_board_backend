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
public class GetLatestBoardListResponseDto extends ResponseDto{
    List<BoardListEntity> boardListEntities = null;
    private GetLatestBoardListResponseDto(List<BoardListEntity> boardListEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardListEntities = boardListEntities;
    }

    public static ResponseEntity<GetLatestBoardListResponseDto> success(List<BoardListEntity> boardListEntities) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetLatestBoardListResponseDto(boardListEntities));
    }
}
