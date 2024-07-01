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
public class GetTop3BoardListResponseDto extends ResponseDto{
    List<PostDto> postList = null;
    private GetTop3BoardListResponseDto(List<PostDto> postList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.postList = postList;
    }

    public static ResponseEntity<GetTop3BoardListResponseDto> success(List<PostDto> postList) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetTop3BoardListResponseDto(postList));
    }
}
