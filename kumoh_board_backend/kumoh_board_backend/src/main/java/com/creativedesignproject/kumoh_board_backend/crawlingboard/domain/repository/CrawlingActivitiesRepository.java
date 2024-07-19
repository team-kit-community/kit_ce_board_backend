package com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.CrawlingActivities;

import java.util.List;

public interface CrawlingActivitiesRepository extends JpaRepository<CrawlingActivities, Long> {
    List<CrawlingActivities> findAll();

    CrawlingActivities findWevityActivityById(Long post_number);

    void deleteByRemainingDaysLessThanEqualActivity(int remainingDaysThreshold);

    boolean existsByUrlActivity(String url);
}
