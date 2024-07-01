package com.creativedesignproject.kumoh_board_backend.Board.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.entity.Comment;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;

import lombok.Getter;

@Getter
public class GetCommentListResponseDto extends ResponseDto {
    private List<Comment> comments;

    private GetCommentListResponseDto(List<Comment> comments) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.comments = comments;
    }

    public static ResponseEntity<GetCommentListResponseDto> success(List<Comment> comments) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetCommentListResponseDto(comments));
    }
}
