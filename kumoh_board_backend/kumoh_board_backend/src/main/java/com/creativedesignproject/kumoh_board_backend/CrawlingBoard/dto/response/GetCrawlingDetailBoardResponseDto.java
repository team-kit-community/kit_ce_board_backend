package com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.common.ResponseMessage;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.CrawlingPostDto;

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
