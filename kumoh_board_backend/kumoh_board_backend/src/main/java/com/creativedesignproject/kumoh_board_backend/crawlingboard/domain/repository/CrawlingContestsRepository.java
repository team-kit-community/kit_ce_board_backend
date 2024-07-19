package com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.CrawlingContests;

import java.util.List;
import java.util.Optional;

public interface CrawlingContestsRepository extends JpaRepository<CrawlingContests, Long> {
    List<CrawlingContests> findAll();

    Optional<CrawlingContests> findById(Long id);

    @Modifying
    @Query("DELETE FROM CrawlingContests cc WHERE cc.date <= :remainingDaysThreshold")
    void deleteByRemainingDaysLessThanEqualContest(@Param("remainingDaysThreshold") int remainingDaysThreshold);

    boolean existsByUrl(String url);
}
