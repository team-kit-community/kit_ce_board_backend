package com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.CrawlingActivities;

import java.util.List;
import java.util.Optional;

public interface CrawlingActivitiesRepository extends JpaRepository<CrawlingActivities, Long> {
    List<CrawlingActivities> findAll();

    Optional<CrawlingActivities> findById(Long id);

    @Modifying
    @Query("DELETE FROM CrawlingActivities ca WHERE ca.date <= :remainingDaysThreshold")
    void deleteByRemainingDaysLessThanEqualActivity(@Param("remainingDaysThreshold") int remainingDaysThreshold);

    boolean existsByUrl(String url);
}
