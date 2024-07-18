package com.creativedesignproject.kumoh_board_backend.crawlingboard.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.CrawlingPostDto;
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
    public List<CrawlingContests> crawlContestsFromWevityList() {
        List<CrawlingContests> contests = crawlingContestsRepository.findAllWevityContests();
        return contests;
    }

    @Override
    public List<CrawlingActivities> crawlActivitiesFromWevityList() {
        List<CrawlingActivities> activities = crawlingActivitiesRepository.findAllWevityActivities();
        return activities;
    }

    @Override
    public List<LinkCareerContests> crawlContestsFromLinkCareerList() {
        List<LinkCareerContests> contests = linkCareerContestsRepository.findAllLinkCareerContests();
        return contests;
    }

    @Override
    public List<LinkCareerActivities> crawlActivitiesFromLinkCareerList() {
        List<LinkCareerActivities> activities = linkCareerActivitiesRepository.findAllLinkCareerActivities();
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
        return crawlingEntity;
    }
}
