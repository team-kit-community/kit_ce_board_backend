package com.creativedesignproject.kumoh_board_backend.crawlingboard.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creativedesignproject.kumoh_board_backend.auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.CrawlingPostDto;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.response.CrawlingActivitiesFromLinkCareerResponseDto;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.response.CrawlingActivitiesFromWevityResponseDto;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.response.CrawlingContestFromLinkCareerResponseDto;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.response.CrawlingContestFromWevityResponseDto;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.response.GetCrawlingDetailBoardResponseDto;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.entity.CrawlingActivities;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.entity.CrawlingContests;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.entity.LinkCareerActivities;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.entity.LinkCareerContests;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.repository.CrawlingActivitiesRepository;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.repository.CrawlingContestsRepository;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.repository.LinkCareerActivitiesRepository;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.repository.LinkCareerContestsRepository;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.service.CrawlingBoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrawlingBoardServiceImpl implements CrawlingBoardService {
    private final CrawlingContestsRepository crawlingContestsRepository;
    private final CrawlingActivitiesRepository crawlingActivitiesRepository;
    private final LinkCareerContestsRepository linkCareerContestsRepository;
    private final LinkCareerActivitiesRepository linkCareerActivitiesRepository;


    @Override
    public ResponseEntity<? super CrawlingContestFromWevityResponseDto> crawlContestsFromWevityList() {
        List<CrawlingContests> contests;
        try {
            contests = crawlingContestsRepository.findAllWevityContests();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return CrawlingContestFromWevityResponseDto.success(contests);
    }

    @Override
    public ResponseEntity<? super CrawlingActivitiesFromWevityResponseDto> crawlActivitiesFromWevityList() {
        List<CrawlingActivities> activities;
        try {
            activities = crawlingActivitiesRepository.findAllWevityActivities();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CrawlingActivitiesFromWevityResponseDto.success(activities);
    }

    @Override
    public ResponseEntity<? super CrawlingContestFromLinkCareerResponseDto> crawlContestsFromLinkCareerList() {
        List<LinkCareerContests> contests;
        try {
            contests = linkCareerContestsRepository.findAllLinkCareerContests();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CrawlingContestFromLinkCareerResponseDto.success(contests);
    }

    @Override
    public ResponseEntity<? super CrawlingActivitiesFromLinkCareerResponseDto> crawlActivitiesFromLinkCareerList() {
        List<LinkCareerActivities> activities;
        try {
            activities = linkCareerActivitiesRepository.findAllLinkCareerActivities();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CrawlingActivitiesFromLinkCareerResponseDto.success(activities);
    }

    @Override
    public ResponseEntity<? super GetCrawlingDetailBoardResponseDto> getCrawlingDetailBoard(Long category_id,
            Long post_number, String type) {
        CrawlingActivities activity;
        CrawlingContests contest;
        LinkCareerActivities linkCareerActivity;
        LinkCareerContests linkCareerContest;
        CrawlingPostDto crawlingEntity = new CrawlingPostDto();
        try {
            if(type.equals("wevityActivity")) {
                activity = crawlingActivitiesRepository.findWevityActivityById(post_number);
                crawlingEntity = CrawlingPostDto.builder()
                        .title(activity.getTitle())
                        .date(activity.getDate())
                        .detailData(activity.getDetailData())
                        .build();
            } else if(type.equals("wevityContest")) {
                contest = crawlingContestsRepository.findWevityContestById(post_number);
                crawlingEntity = CrawlingPostDto.builder()
                        .title(contest.getTitle())
                        .date(contest.getDate())
                        .detailData(contest.getDetailData())
                        .build();
            } else if(type.equals("linkCareerActivity")) {
                linkCareerActivity = linkCareerActivitiesRepository.findLinkCareerActivityById(post_number);
                crawlingEntity = CrawlingPostDto.builder()
                        .title(linkCareerActivity.getTitle())
                        .date(linkCareerActivity.getDate())
                        .detailData(linkCareerActivity.getDetailData())
                        .build();
            } else if(type.equals("linkCareerContest")) {
                linkCareerContest = linkCareerContestsRepository.findLinkCareerContestById(post_number);
                crawlingEntity = CrawlingPostDto.builder()
                        .title(linkCareerContest.getTitle())
                        .date(linkCareerContest.getDate())
                        .detailData(linkCareerContest.getDetailData())
                        .build();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetCrawlingDetailBoardResponseDto.success(crawlingEntity);
    }
    
}
