package com.creativedesignproject.kumoh_board_backend.crawlingboard.presentation;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.CrawlingActivities;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.CrawlingContests;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.LinkCareerActivities;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.LinkCareerContests;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.CrawlingPostDto;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.service.CrawlingBoardService;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.service.CrawlingService;

import lombok.RequiredArgsConstructor;

import java.util.List;

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
    public ResponseEntity<List<CrawlingContests>> crawlContestsFromWevityList() {
        List<CrawlingContests> crawlingContests = crawlingBoardService.crawlContestsFromWevityList();
        return ResponseEntity.ok().body(crawlingContests);
    }

    @GetMapping("/crawlActivitiesFromWevityList")
    public ResponseEntity<List<CrawlingActivities>> crawlActivitiesFromWevityList() {
        List<CrawlingActivities> crawlingActivities = crawlingBoardService.crawlActivitiesFromWevityList();
        return ResponseEntity.ok().body(crawlingActivities);
    }

    @GetMapping("/crawlContestsFromLinkCareerList")
    public ResponseEntity<List<LinkCareerContests>> crawlContestsFromLinkCareerList() {
        List<LinkCareerContests> linkCareerContests = crawlingBoardService.crawlContestsFromLinkCareerList();
        return ResponseEntity.ok().body(linkCareerContests);
    }

    @GetMapping("/crawlActivitiesFromLinkCareerList")
    public ResponseEntity<List<LinkCareerActivities>> crawlActivitiesFromLinkCareerList() {
        List<LinkCareerActivities> linkCareerActivities = crawlingBoardService.crawlActivitiesFromLinkCareerList();
        return ResponseEntity.ok().body(linkCareerActivities);
    }

    @GetMapping("{category_id}/{post_number}/crawlPostDetail/{type}")
    public ResponseEntity<CrawlingPostDto> getCrawlingDetailBoard(
        @PathVariable("category_id") Long category_id,
        @PathVariable("post_number") Long post_number,
        @PathVariable("type") String type
    ) {
        CrawlingPostDto crawlingPostDto = crawlingBoardService.getCrawlingDetailBoard(category_id, post_number, type);
        return ResponseEntity.ok().body(crawlingPostDto);
    }
}
