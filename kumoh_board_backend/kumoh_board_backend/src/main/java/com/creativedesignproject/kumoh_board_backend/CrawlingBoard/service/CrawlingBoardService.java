package com.creativedesignproject.kumoh_board_backend.crawlingboard.service;

import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.CrawlingActivities;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.CrawlingContests;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.LinkCareerActivities;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.LinkCareerContests;
import com.creativedesignproject.kumoh_board_backend.crawlingboard.dto.CrawlingPostDto;

import java.util.List;

public interface CrawlingBoardService {
    List<CrawlingContests> crawlContestsFromWevityList();
    List<CrawlingActivities> crawlActivitiesFromWevityList();
    List<LinkCareerContests> crawlContestsFromLinkCareerList();
    List<LinkCareerActivities> crawlActivitiesFromLinkCareerList();
    CrawlingPostDto getCrawlingDetailBoard(Long category_id, Long post_number, String type);
}

