package com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.common.ResponseMessage;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.entity.LinkCareerActivities;

import lombok.Getter;

@Getter
public class CrawlingActivitiesFromLinkCareerResponseDto extends ResponseDto{
    private List<LinkCareerActivities> activities;

    private CrawlingActivitiesFromLinkCareerResponseDto(List<LinkCareerActivities> activities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.activities = activities;
    }

    public static ResponseEntity<CrawlingActivitiesFromLinkCareerResponseDto> success(List<LinkCareerActivities> activities) {
        return ResponseEntity.status(HttpStatus.OK).body(new CrawlingActivitiesFromLinkCareerResponseDto(activities));
    }
}
