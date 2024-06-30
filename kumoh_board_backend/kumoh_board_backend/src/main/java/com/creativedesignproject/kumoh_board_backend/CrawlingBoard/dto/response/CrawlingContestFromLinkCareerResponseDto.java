package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.LinkCareerContestsEntity;

import lombok.Getter;

@Getter
public class CrawlingContestFromLinkCareerResponseDto extends ResponseDto{
    private List<LinkCareerContestsEntity> contests;

    private CrawlingContestFromLinkCareerResponseDto(List<LinkCareerContestsEntity> contests) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.contests = contests;
    }

    public static ResponseEntity<CrawlingContestFromLinkCareerResponseDto> success(List<LinkCareerContestsEntity> contests) {
        return ResponseEntity.status(HttpStatus.OK).body(new CrawlingContestFromLinkCareerResponseDto(contests));
    }
}