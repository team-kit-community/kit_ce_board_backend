package com.creativedesignproject.kumoh_board_backend.crawlingboard.service;

import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.CrawlingPostDto;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.entity.CrawlingActivities;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.entity.CrawlingContests;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.entity.LinkCareerActivities;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.entity.LinkCareerContests;

import java.util.List;

public interface CrawlingBoardService {
    List<CrawlingContests> crawlContestsFromWevityList();
    List<CrawlingActivities> crawlActivitiesFromWevityList();
    List<LinkCareerContests> crawlContestsFromLinkCareerList();
    List<LinkCareerActivities> crawlActivitiesFromLinkCareerList();
    CrawlingPostDto getCrawlingDetailBoard(Long category_id, Long post_number, String type);
}

