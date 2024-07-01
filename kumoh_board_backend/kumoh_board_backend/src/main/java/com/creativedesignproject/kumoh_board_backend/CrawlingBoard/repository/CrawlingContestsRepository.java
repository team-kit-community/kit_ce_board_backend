package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.repository;

import org.springframework.stereotype.Repository;

import com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity.CrawlingContests;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CrawlingContestsRepository {
    private final EntityManager em;

    public List<CrawlingContests> findAllWevityContests() {
        return em.createQuery("select c from CrawlingContests c", CrawlingContests.class)
            .getResultList();
    }

    public CrawlingContests findWevityContestById(Long post_number) {
        return em.find(CrawlingContests.class, post_number);
    }

    public void deleteByRemainingDaysLessThanEqualContest(int remainingDaysThreshold) {
        em.createQuery("delete from CrawlingContests c where c.date <= :remainingDaysThreshold")
            .setParameter("remainingDaysThreshold", remainingDaysThreshold)
            .executeUpdate();
    }

    public boolean existsByUrlContest(String url) {
        return em.createQuery("select count(c) from CrawlingContests c where c.url = :url", Long.class)
            .setParameter("url", url)
            .getSingleResult() > 0;
    }

    public void save(CrawlingContests crawlingEntity) {
        em.persist(crawlingEntity);
    }
}
