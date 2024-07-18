package com.creativedesignproject.kumoh_board_backend.crawlingboard.repository;

import org.springframework.stereotype.Repository;

import com.creativedesignproject.kumoh_board_backend.crawlingboard.entity.CrawlingActivities;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CrawlingActivitiesRepository {
    private final EntityManager em;

    public List<CrawlingActivities> findAllWevityActivities() {
        return em.createQuery("select a from CrawlingActivities a", CrawlingActivities.class)
            .getResultList();
    }

    public CrawlingActivities findWevityActivityById(Long post_number) {
        return em.find(CrawlingActivities.class, post_number);
    }

    public void deleteByRemainingDaysLessThanEqualActivity(int remainingDaysThreshold) {
        em.createQuery("delete from CrawlingActivities a where a.date <= :remainingDaysThreshold")
            .setParameter("remainingDaysThreshold", remainingDaysThreshold)
            .executeUpdate();
    }

    public boolean existsByUrlActivity(String url) {
        return em.createQuery("select count(a) from CrawlingActivities a where a.url = :url", Long.class)
            .setParameter("url", url)
            .getSingleResult() > 0;
    }

    public void save(CrawlingActivities crawlingEntity) {
        em.persist(crawlingEntity);
    }
}
