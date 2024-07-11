package com.creativedesignproject.kumoh_board_backend.crawlingboard.service;

import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.response.CrawlingActivitiesFromLinkCareerResponseDto;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.response.CrawlingActivitiesFromWevityResponseDto;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.response.CrawlingContestFromLinkCareerResponseDto;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.response.CrawlingContestFromWevityResponseDto;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.response.GetCrawlingDetailBoardResponseDto;

public interface CrawlingBoardService {
    ResponseEntity<? super CrawlingContestFromWevityResponseDto> crawlContestsFromWevityList();
    ResponseEntity<? super CrawlingActivitiesFromWevityResponseDto> crawlActivitiesFromWevityList();
    ResponseEntity<? super CrawlingContestFromLinkCareerResponseDto> crawlContestsFromLinkCareerList();
    ResponseEntity<? super CrawlingActivitiesFromLinkCareerResponseDto> crawlActivitiesFromLinkCareerList();
    ResponseEntity<? super GetCrawlingDetailBoardResponseDto> getCrawlingDetailBoard(Long category_id, Long post_number, String type);
}

