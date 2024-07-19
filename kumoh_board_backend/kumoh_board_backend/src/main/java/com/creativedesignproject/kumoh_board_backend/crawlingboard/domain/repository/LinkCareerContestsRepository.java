package com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.LinkCareerContests;

import java.util.List;

public interface LinkCareerContestsRepository extends JpaRepository<LinkCareerContests, Long> {
    List<LinkCareerContests> findAll();

    LinkCareerContests findLinkCareerContestById(Long post_number);

    void deleteByRemainingDaysLessThanEqualContest(int remainingDaysThreshold);

    boolean existsByUrlContest(String url);

    LinkCareerContests findByUrlLinkCareerContest(String detailUrl);

    void saveAll(List<LinkCareerContests> entitiesToUpdate);
}
