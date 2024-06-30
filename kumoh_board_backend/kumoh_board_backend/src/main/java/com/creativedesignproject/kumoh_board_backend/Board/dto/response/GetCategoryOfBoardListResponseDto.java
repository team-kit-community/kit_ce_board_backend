package com.creativedesignproject.kumoh_board_backend.Board.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.entity.CategoryBoardListEntity;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;

import lombok.Getter;

@Getter
public class GetCategoryOfBoardListResponseDto extends ResponseDto{
    List<CategoryBoardListEntity> categoryOfBoardList = null;

    private GetCategoryOfBoardListResponseDto(List<CategoryBoardListEntity> boardListEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.categoryOfBoardList = boardListEntities;
    }

    public static ResponseEntity<GetCategoryOfBoardListResponseDto> success(List<CategoryBoardListEntity> boardListEntities) {
        GetCategoryOfBoardListResponseDto responseBody = new GetCategoryOfBoardListResponseDto(boardListEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistedCategory() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_CATEGORY,
                ResponseMessage.NOT_EXISTED_CATEGORY);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistedBoard() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
