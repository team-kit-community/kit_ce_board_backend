package com.creativedesignproject.kumoh_board_backend.crawlingboard.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.CrawlingActivities;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.CrawlingContests;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.LinkCareerActivities;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.LinkCareerContests;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.repository.CrawlingActivitiesRepository;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.repository.CrawlingContestsRepository;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.repository.LinkCareerActivitiesRepository;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.repository.LinkCareerContestsRepository;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.CrawlingPostDto;
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
    public List<CrawlingContests> crawlContestsFromWevityList() {
        List<CrawlingContests> contests = crawlingContestsRepository.findAll();
        return contests;
    }

    @Override
    public List<CrawlingActivities> crawlActivitiesFromWevityList() {
        List<CrawlingActivities> activities = crawlingActivitiesRepository.findAll();
        return activities;
    }

    @Override
    public List<LinkCareerContests> crawlContestsFromLinkCareerList() {
        List<LinkCareerContests> contests = linkCareerContestsRepository.findAll();
        return contests;
    }

    @Override
    public List<LinkCareerActivities> crawlActivitiesFromLinkCareerList() {
        List<LinkCareerActivities> activities = linkCareerActivitiesRepository.findAll();
        return activities;
    }

    @Override
    public CrawlingPostDto getCrawlingDetailBoard(Long category_id,
            Long post_number, String type) {
        CrawlingActivities activity;
        CrawlingContests contest;
        LinkCareerActivities linkCareerActivity;
        LinkCareerContests linkCareerContest;
        CrawlingPostDto crawlingEntity = new CrawlingPostDto();

        if(type.equals("wevityActivity")) {
            activity = crawlingActivitiesRepository.findById(post_number).get();
                crawlingEntity = CrawlingPostDto.builder()
                        .title(activity.getTitle())
                        .date(activity.getDate())
                        .detailData(activity.getDetailData())
                        .build();
            } else if(type.equals("wevityContest")) {
                contest = crawlingContestsRepository.findById(post_number).get();
                crawlingEntity = CrawlingPostDto.builder()
                        .title(contest.getTitle())
                        .date(contest.getDate())
                        .detailData(contest.getDetailData())
                        .build();
            } else if(type.equals("linkCareerActivity")) {
                linkCareerActivity = linkCareerActivitiesRepository.findById(post_number).get();
                crawlingEntity = CrawlingPostDto.builder()
                        .title(linkCareerActivity.getTitle())
                        .date(linkCareerActivity.getDate())
                        .detailData(linkCareerActivity.getDetailData())
                        .build();
            } else if(type.equals("linkCareerContest")) {
                linkCareerContest = linkCareerContestsRepository.findById(post_number).get();
                crawlingEntity = CrawlingPostDto.builder()
                        .title(linkCareerContest.getTitle())
                        .date(linkCareerContest.getDate())
                        .detailData(linkCareerContest.getDetailData())
                        .build();
            }
        return crawlingEntity;
    }
}
