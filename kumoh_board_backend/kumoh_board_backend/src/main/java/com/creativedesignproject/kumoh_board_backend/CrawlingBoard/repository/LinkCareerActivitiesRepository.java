package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.repository;

import org.springframework.stereotype.Repository;

import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.LinkCareerActivities;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LinkCareerActivitiesRepository {
    private final EntityManager em;

    public List<LinkCareerActivities> findAllLinkCareerActivities() {
        return em.createQuery("select a from LinkCareerActivities a", LinkCareerActivities.class)
            .getResultList();
    }

    public LinkCareerActivities findLinkCareerActivityById(Long post_number) {
        return em.find(LinkCareerActivities.class, post_number);
    }

    public List<String> findAllLinkCareerActivitiesUrls() {
        return em.createQuery("select a.url from LinkCareerActivities a", String.class)
            .getResultList();
    }

    public void save(LinkCareerActivities crawlingEntity) {
        em.persist(crawlingEntity);
    }

    public void saveAll(List<LinkCareerActivities> entitiesToUpdate) {
        em.persist(entitiesToUpdate);
    }

    public LinkCareerActivities findByUrlLinkCareerActivity(String detailUrl) {
        return em.createQuery("select a from LinkCareerActivities a where a.url = :detailUrl", LinkCareerActivities.class)
            .setParameter("detailUrl", detailUrl)
            .getSingleResult();
    }
}
