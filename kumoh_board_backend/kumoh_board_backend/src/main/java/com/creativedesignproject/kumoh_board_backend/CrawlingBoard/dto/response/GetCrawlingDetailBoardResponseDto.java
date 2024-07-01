package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.CrawlingPostDto;

import lombok.Getter;

@Getter
public class GetCrawlingDetailBoardResponseDto extends ResponseDto {
    private CrawlingPostDto crawling;

    private GetCrawlingDetailBoardResponseDto(CrawlingPostDto crawling) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.crawling = crawling;
    }

    public static ResponseEntity<GetCrawlingDetailBoardResponseDto> success(CrawlingPostDto crawlingEntity) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetCrawlingDetailBoardResponseDto(crawlingEntity));
    }
}
