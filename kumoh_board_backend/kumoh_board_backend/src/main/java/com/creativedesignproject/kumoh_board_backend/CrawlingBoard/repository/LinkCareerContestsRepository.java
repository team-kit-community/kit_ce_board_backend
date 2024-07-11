package com.creativedesignproject.kumoh_board_backend.crawlingboard.repository;

import org.springframework.stereotype.Repository;

import com.creativedesignproject.kumoh_board_backend.crawlingboard.entity.LinkCareerContests;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LinkCareerContestsRepository {
    private final EntityManager em;

    public List<LinkCareerContests> findAllLinkCareerContests() {
        return em.createQuery("select c from LinkCareerContests c", LinkCareerContests.class)
            .getResultList();
    }

    public LinkCareerContests findLinkCareerContestById(Long post_number) {
        return em.find(LinkCareerContests.class, post_number);
    }

    public List<String> findAllLinkCareerContestUrls() {
        return em.createQuery("select c.url from LinkCareerContests c", String.class)
            .getResultList();
    }

    public void save(LinkCareerContests crawlingEntity) {
        em.persist(crawlingEntity);
    }

    public LinkCareerContests findByUrlLinkCareerContest(String detailUrl) {
        return em.createQuery("select c from LinkCareerContests c where c.url = :detailUrl", LinkCareerContests.class)
            .setParameter("detailUrl", detailUrl)
            .getSingleResult();
    }

    public void saveAll(List<LinkCareerContests> entitiesToUpdate) {
        em.persist(entitiesToUpdate);
    }
}
