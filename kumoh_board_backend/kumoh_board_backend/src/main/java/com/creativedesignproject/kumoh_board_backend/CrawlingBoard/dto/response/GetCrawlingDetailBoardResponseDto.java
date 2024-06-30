package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.CrawlingPostEntity;

import lombok.Getter;

@Getter
public class GetCrawlingDetailBoardResponseDto extends ResponseDto {
    private CrawlingPostEntity crawlingEntity;

    private GetCrawlingDetailBoardResponseDto(CrawlingPostEntity crawlingEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.crawlingEntity = crawlingEntity;
    }

    public static ResponseEntity<GetCrawlingDetailBoardResponseDto> success(CrawlingPostEntity crawlingEntity) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetCrawlingDetailBoardResponseDto(crawlingEntity));
    }
}
