package com.creativedesignproject.kumoh_board_backend.auth.repository;

import org.springframework.stereotype.Repository;

import com.creativedesignproject.kumoh_board_backend.auth.domain.Certification;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CertificationRepository {
    private final EntityManager em;

    public void save(Certification certification) {
        em.persist(certification);
    }

    public Certification findByEmail(String email) {
        return em.find(Certification.class, email);
    }

    public void deleteByCertificationUserEmail(String email) {
        em.createQuery("delete from Certification c where c.user.email = :email")
                .setParameter("email", email)
                .executeUpdate();
    }
}
