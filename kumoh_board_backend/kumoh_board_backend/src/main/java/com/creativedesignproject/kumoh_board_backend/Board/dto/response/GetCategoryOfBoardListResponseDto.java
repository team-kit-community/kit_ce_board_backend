package com.creativedesignproject.kumoh_board_backend.Board.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.repository.query.CategoryPostDto;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;

import lombok.Getter;

@Getter
public class GetCategoryOfBoardListResponseDto extends ResponseDto{
    List<CategoryPostDto> postList = null;

    private GetCategoryOfBoardListResponseDto(List<CategoryPostDto> postList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.postList = postList;
    }

    public static ResponseEntity<GetCategoryOfBoardListResponseDto> success(List<CategoryPostDto> postList) {
        GetCategoryOfBoardListResponseDto responseBody = new GetCategoryOfBoardListResponseDto(postList);
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
