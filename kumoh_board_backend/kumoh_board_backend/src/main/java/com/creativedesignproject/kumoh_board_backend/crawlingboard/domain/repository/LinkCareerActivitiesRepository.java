package com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.LinkCareerActivities;

import java.util.List;

public interface LinkCareerActivitiesRepository extends JpaRepository<LinkCareerActivities, Long> {

    List<LinkCareerActivities> findAll();

    LinkCareerActivities findLinkCareerActivityById(Long post_number);

    void deleteByRemainingDaysLessThanEqualActivity(int remainingDaysThreshold);

    boolean existsByUrlActivity(String url);

    void saveAll(List<LinkCareerActivities> entitiesToUpdate);

    LinkCareerActivities findByUrlLinkCareerActivity(String detailUrl);
}
