package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseCode;
import com.creativedesignproject.kumoh_board_backend.Common.ResponseMessage;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.CrawlingActivitiesEntity;

import lombok.Getter;

@Getter
public class CrawlingActivitiesFromWevityResponseDto extends ResponseDto{
    private List<CrawlingActivitiesEntity> activities;

    private CrawlingActivitiesFromWevityResponseDto(List<CrawlingActivitiesEntity> activities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.activities = activities;
    }

    public static ResponseEntity<CrawlingActivitiesFromWevityResponseDto> success(List<CrawlingActivitiesEntity> activities) {
        return ResponseEntity.status(HttpStatus.OK).body(new CrawlingActivitiesFromWevityResponseDto(activities));
    }
}
