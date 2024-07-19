package com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.LinkCareerActivities;

import java.util.List;
import java.util.Optional;

public interface LinkCareerActivitiesRepository extends JpaRepository<LinkCareerActivities, Long> {

    List<LinkCareerActivities> findAll();

    Optional<LinkCareerActivities> findById(Long id);

    @Modifying
    @Query("DELETE FROM LinkCareerActivities lca WHERE lca.date <= :remainingDaysThreshold")
    void deleteByRemainingDaysLessThanEqualActivity(@Param("remainingDaysThreshold") int remainingDaysThreshold);

    boolean existsByUrl(String url);

    //void saveAll(List<LinkCareerActivities> entitiesToUpdate);

    LinkCareerActivities findByUrl(String detailUrl);
}
