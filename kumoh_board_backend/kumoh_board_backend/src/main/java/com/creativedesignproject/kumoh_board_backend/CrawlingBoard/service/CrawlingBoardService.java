package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.service;

import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response.CrawlingActivitiesFromLinkCareerResponseDto;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response.CrawlingActivitiesFromWevityResponseDto;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response.CrawlingContestFromLinkCareerResponseDto;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response.CrawlingContestFromWevityResponseDto;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response.GetCrawlingDetailBoardResponseDto;

import org.springframework.http.ResponseEntity;

public interface CrawlingBoardService {
    ResponseEntity<? super CrawlingContestFromWevityResponseDto> crawlContestsFromWevityList();
    ResponseEntity<? super CrawlingActivitiesFromWevityResponseDto> crawlActivitiesFromWevityList();
    ResponseEntity<? super CrawlingContestFromLinkCareerResponseDto> crawlContestsFromLinkCareerList();
    ResponseEntity<? super CrawlingActivitiesFromLinkCareerResponseDto> crawlActivitiesFromLinkCareerList();
    ResponseEntity<? super GetCrawlingDetailBoardResponseDto> getCrawlingDetailBoard(Long category_id, Long post_number, String type);
}

