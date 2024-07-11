package com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.common.ResponseMessage;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.entity.LinkCareerContests;

import lombok.Getter;

@Getter
public class CrawlingContestFromLinkCareerResponseDto extends ResponseDto{
    private List<LinkCareerContests> contests;

    private CrawlingContestFromLinkCareerResponseDto(List<LinkCareerContests> contests) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.contests = contests;
    }

    public static ResponseEntity<CrawlingContestFromLinkCareerResponseDto> success(List<LinkCareerContests> contests) {
        return ResponseEntity.status(HttpStatus.OK).body(new CrawlingContestFromLinkCareerResponseDto(contests));
    }
}