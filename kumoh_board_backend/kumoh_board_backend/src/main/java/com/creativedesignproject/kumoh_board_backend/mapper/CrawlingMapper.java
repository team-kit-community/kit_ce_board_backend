package com.creativedesignproject.kumoh_board_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.CrawlingActivitiesEntity;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.CrawlingContestsEntity;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.LinkCareerActivitiesEntity;
import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.LinkCareerContestsEntity;

@Mapper
public interface CrawlingMapper {
    //위비티 공모전 관련 인터페이스
    public void save(CrawlingContestsEntity entity);

    public boolean existsByUrlContest(@Param("url") String url);

    public void deleteByRemainingDaysLessThanEqualContest(@Param("remainingDaysThreshold") int remainingDaysThreshold);

    //위비티 대외활동 관련 인터페이스

    public void saveActivity(CrawlingActivitiesEntity entity);

    public boolean existsByUrlActivity(@Param("url") String url);

    public void deleteByRemainingDaysLessThanEqualActivity(@Param("remainingDaysThreshold") int remainingDaysThreshold);

    //링커리어 공모전 관련 인터페이스
    public List<String> findAllLinkCareerContestUrls();

    public LinkCareerContestsEntity findByUrlLinkCareerContest(@Param("url") String url);

    public void saveLinkCareerContest(LinkCareerContestsEntity crawlingEntity);

    @Transactional
    public void saveAllLinkCareerContests(@Param("entitiesToUpdate") List<LinkCareerContestsEntity> entitiesToUpdate);

    //링커리어 대외활동 관련 인터페이스
    @Transactional
    public void saveAllLinkCareerActivities(@Param("entities") List<LinkCareerActivitiesEntity> entities);
    
    public LinkCareerActivitiesEntity findByUrlLinkCareerActivity(@Param("url") String url);
    
    public void saveLinkCareerActivity(@Param("entities") LinkCareerActivitiesEntity entities);
    
    public List<String> findAllLinkCareerActivitiesUrls();

    //위비티 공모전 리스트 관련 인터페이스
    public List<CrawlingContestsEntity> findAllWevityContests();

    //위비티 대외활동 리스트 관련 인터페이스
    public List<CrawlingActivitiesEntity> findAllWevityActivities();

    //링커리어 공모전 리스트 관련 인터페이스
    public List<LinkCareerContestsEntity> findAllLinkCareerContests();

    //링커리어 대외활동 리스트 관련 인터페이스
    public List<LinkCareerActivitiesEntity> findAllLinkCareerActivities();

    // 크롤링 상세 게시물 불러오기
    public CrawlingContestsEntity findWevityContestById(@Param("post_number") int post_number);

    public CrawlingActivitiesEntity findWevityActivityById(@Param("post_number") int post_number);

    public LinkCareerContestsEntity findLinkCareerContestById(@Param("post_number") int post_number);

    public LinkCareerActivitiesEntity findLinkCareerActivityById(@Param("post_number") int post_number);
}
