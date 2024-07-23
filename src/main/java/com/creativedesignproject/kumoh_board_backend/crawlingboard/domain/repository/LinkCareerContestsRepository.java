package com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.creativedesignproject.kumoh_board_backend.crawlingboard.domain.entity.LinkCareerContests;

import java.util.List;
import java.util.Optional;

public interface LinkCareerContestsRepository extends JpaRepository<LinkCareerContests, Long> {
    List<LinkCareerContests> findAll();

    Optional<LinkCareerContests> findById(Long id);

    @Modifying
    @Query("DELETE FROM LinkCareerContests lcc WHERE lcc.date <= :remainingDaysThreshold")
    void deleteByRemainingDaysLessThanEqualContest(@Param("remainingDaysThreshold") int remainingDaysThreshold);

    boolean existsByUrl(String url);

    LinkCareerContests findByUrl(String detailUrl);

    //void saveAll(List<LinkCareerContests> entitiesToUpdate);
}
