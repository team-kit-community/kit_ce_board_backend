package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.CrawlingContestsEntity;

import lombok.Getter;

@Getter
public class CrawlingContestFromWevityResponseDto extends ResponseDto {
    private List<CrawlingContestsEntity> contests;

    private CrawlingContestFromWevityResponseDto(List<CrawlingContestsEntity> contests) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.contests = contests;
    }

    public static ResponseEntity<CrawlingContestFromWevityResponseDto> success(List<CrawlingContestsEntity> contests) {
        return ResponseEntity.status(HttpStatus.OK).body(new CrawlingContestFromWevityResponseDto(contests));
    }
}