package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.service.Impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.CrawlingPostEntity;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response.CrawlingActivitiesFromLinkCareerResponseDto;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response.CrawlingActivitiesFromWevityResponseDto;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response.CrawlingContestFromLinkCareerResponseDto;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response.CrawlingContestFromWevityResponseDto;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto.response.GetCrawlingDetailBoardResponseDto;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.service.CrawlingBoardService;
import com.creativedesignproject.kumoh_board_backend.mapper.CrawlingMapper;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.CrawlingContestsEntity;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.LinkCareerActivitiesEntity;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.LinkCareerContestsEntity;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.CrawlingActivitiesEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrawlingBoardServiceImpl implements CrawlingBoardService {
    private final CrawlingMapper crawlingMapper;

    @Override
    public ResponseEntity<? super CrawlingContestFromWevityResponseDto> crawlContestsFromWevityList() {
        List<CrawlingContestsEntity> contests;
        try {
            contests = crawlingMapper.findAllWevityContests();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return CrawlingContestFromWevityResponseDto.success(contests);
    }

    @Override
    public ResponseEntity<? super CrawlingActivitiesFromWevityResponseDto> crawlActivitiesFromWevityList() {
        List<CrawlingActivitiesEntity> activities;
        try {
            activities = crawlingMapper.findAllWevityActivities();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CrawlingActivitiesFromWevityResponseDto.success(activities);
    }

    @Override
    public ResponseEntity<? super CrawlingContestFromLinkCareerResponseDto> crawlContestsFromLinkCareerList() {
        List<LinkCareerContestsEntity> contests;
        try {
            contests = crawlingMapper.findAllLinkCareerContests();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CrawlingContestFromLinkCareerResponseDto.success(contests);
    }

    @Override
    public ResponseEntity<? super CrawlingActivitiesFromLinkCareerResponseDto> crawlActivitiesFromLinkCareerList() {
        List<LinkCareerActivitiesEntity> activities;
        try {
            activities = crawlingMapper.findAllLinkCareerActivities();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CrawlingActivitiesFromLinkCareerResponseDto.success(activities);
    }

    @Override
    public ResponseEntity<? super GetCrawlingDetailBoardResponseDto> getCrawlingDetailBoard(int category_id,
            int post_number, String type) {
        CrawlingActivitiesEntity activity;
        CrawlingContestsEntity contest;
        LinkCareerActivitiesEntity linkCareerActivity;
        LinkCareerContestsEntity linkCareerContest;
        CrawlingPostEntity crawlingEntity = new CrawlingPostEntity();
        try {
            if(type.equals("wevityActivity")) {
                activity = crawlingMapper.findWevityActivityById(post_number);
                crawlingEntity = CrawlingPostEntity.builder()
                        .title(activity.getTitle())
                        .date(activity.getDate())
                        .detailData(activity.getDetailData())
                        .build();
            } else if(type.equals("wevityContest")) {
                contest = crawlingMapper.findWevityContestById(post_number);
                crawlingEntity = CrawlingPostEntity.builder()
                        .title(contest.getTitle())
                        .date(contest.getDate())
                        .detailData(contest.getDetailData())
                        .build();
            } else if(type.equals("linkCareerActivity")) {
                linkCareerActivity = crawlingMapper.findLinkCareerActivityById(post_number);
                crawlingEntity = CrawlingPostEntity.builder()
                        .title(linkCareerActivity.getTitle())
                        .date(linkCareerActivity.getDate())
                        .detailData(linkCareerActivity.getDetailData())
                        .build();
            } else if(type.equals("linkCareerContest")) {
                linkCareerContest = crawlingMapper.findLinkCareerContestById(post_number);
                crawlingEntity = CrawlingPostEntity.builder()
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
