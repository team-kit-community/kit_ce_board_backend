package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.service;

import java.io.IOException;

public interface CrawlingService {
    void crawlContestsFromWevity() throws IOException;
    void crawlActivitiesFromWevity() throws IOException;
    void crawlContestsFromLinkCareer() throws IOException;
    void crawlActivitiesFromLinkCareer() throws IOException;
}