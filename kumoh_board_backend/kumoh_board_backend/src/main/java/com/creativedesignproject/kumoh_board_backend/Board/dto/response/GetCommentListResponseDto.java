package com.creativedesignproject.kumoh_board_backend.Board.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.entity.CommentEntity;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;

import lombok.Getter;

@Getter
public class GetCommentListResponseDto extends ResponseDto {
    private List<CommentEntity> comments;

    private GetCommentListResponseDto(List<CommentEntity> comments) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.comments = comments;
    }

    public static ResponseEntity<GetCommentListResponseDto> success(List<CommentEntity> comments) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetCommentListResponseDto(comments));
    }
}
