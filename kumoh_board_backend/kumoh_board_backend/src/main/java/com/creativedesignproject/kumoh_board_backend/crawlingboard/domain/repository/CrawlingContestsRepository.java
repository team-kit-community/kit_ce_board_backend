package com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.CrawlingContests;

import java.util.List;

public interface CrawlingContestsRepository extends JpaRepository<CrawlingContests, Long> {
    List<CrawlingContests> findAll();

    CrawlingContests findWevityContestById(Long post_number);

    void deleteByRemainingDaysLessThanEqualContest(int remainingDaysThreshold);

    boolean existsByUrlContest(String url);
}
