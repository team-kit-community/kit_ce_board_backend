package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.service.CrawlingBoardService;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.service.CrawlingService;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response.CrawlingContestFromWevityResponseDto;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response.CrawlingActivitiesFromWevityResponseDto;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response.CrawlingContestFromLinkCareerResponseDto;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response.CrawlingActivitiesFromLinkCareerResponseDto;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response.GetCrawlingDetailBoardResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/crawling")
@RequiredArgsConstructor
public class CrawlingController {
    private final CrawlingService crawlingService;
    private final CrawlingBoardService crawlingBoardService;

    @GetMapping("/crawlContestsFromWevity")
    public void contest() throws IOException {
        crawlingService.crawlContestsFromWevity();
    }

    @GetMapping("/crawlActivitiesFromWevity")
    public void activate() throws IOException {
        crawlingService.crawlActivitiesFromWevity();
    }

    @GetMapping("/crawlContestsFromLinkCareer")
    public void LinkCareerContest() throws IOException {
        crawlingService.crawlContestsFromLinkCareer();
    }

    @GetMapping("/crawlActivitiesFromLinkCareer")
    public void LinkCareerActivate() throws IOException {
        crawlingService.crawlActivitiesFromLinkCareer();
    }

    @GetMapping("/crawlContestsFromWevityList")
    public ResponseEntity<? super CrawlingContestFromWevityResponseDto> crawlContestsFromWevityList() {
        return crawlingBoardService.crawlContestsFromWevityList();
    }

    @GetMapping("/crawlActivitiesFromWevityList")
    public ResponseEntity<? super CrawlingActivitiesFromWevityResponseDto> crawlActivitiesFromWevityList() {
        return crawlingBoardService.crawlActivitiesFromWevityList();
    }

    @GetMapping("/crawlContestsFromLinkCareerList")
    public ResponseEntity<? super CrawlingContestFromLinkCareerResponseDto> crawlContestsFromLinkCareerList() {
        return crawlingBoardService.crawlContestsFromLinkCareerList();
    }

    @GetMapping("/crawlActivitiesFromLinkCareerList")
    public ResponseEntity<? super CrawlingActivitiesFromLinkCareerResponseDto> crawlActivitiesFromLinkCareerList() {
        return crawlingBoardService.crawlActivitiesFromLinkCareerList();
    }

    @GetMapping("{category_id}/{post_number}/crawlPostDetail/{type}")
    public ResponseEntity<? super GetCrawlingDetailBoardResponseDto> getCrawlingDetailBoard(
        @PathVariable("category_id") int category_id,
        @PathVariable("post_number") int post_number,
        @PathVariable("type") String type
    ) {
        return crawlingBoardService.getCrawlingDetailBoard(category_id, post_number, type);
    }
}
