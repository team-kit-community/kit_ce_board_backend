package com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.common.ResponseMessage;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.entity.CrawlingContests;

import lombok.Getter;

@Getter
public class CrawlingContestFromWevityResponseDto extends ResponseDto {
    private List<CrawlingContests> contests;

    private CrawlingContestFromWevityResponseDto(List<CrawlingContests> contests) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.contests = contests;
    }

    public static ResponseEntity<CrawlingContestFromWevityResponseDto> success(List<CrawlingContests> contests) {
        return ResponseEntity.status(HttpStatus.OK).body(new CrawlingContestFromWevityResponseDto(contests));
    }
}