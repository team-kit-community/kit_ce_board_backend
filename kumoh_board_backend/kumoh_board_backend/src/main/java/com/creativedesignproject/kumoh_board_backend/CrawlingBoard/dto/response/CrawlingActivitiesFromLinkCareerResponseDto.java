package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.LinkCareerActivitiesEntity;

import lombok.Getter;

@Getter
public class CrawlingActivitiesFromLinkCareerResponseDto extends ResponseDto{
    private List<LinkCareerActivitiesEntity> activities;

    private CrawlingActivitiesFromLinkCareerResponseDto(List<LinkCareerActivitiesEntity> activities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.activities = activities;
    }

    public static ResponseEntity<CrawlingActivitiesFromLinkCareerResponseDto> success(List<LinkCareerActivitiesEntity> activities) {
        return ResponseEntity.status(HttpStatus.OK).body(new CrawlingActivitiesFromLinkCareerResponseDto(activities));
    }
}
