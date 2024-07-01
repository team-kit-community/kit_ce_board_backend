package com.creativedesignproject.kumoh_board_backend.Board.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.repository.query.PostDto;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;

import lombok.Getter;

@Getter
public class GetUserBoardListResponseDto extends ResponseDto {
    List<PostDto> postList = null;
    
    private GetUserBoardListResponseDto(List<PostDto> postList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.postList = postList;
    }

    public static ResponseEntity<GetUserBoardListResponseDto> success(List<PostDto> postList) {
        GetUserBoardListResponseDto responseBody = new GetUserBoardListResponseDto(postList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}