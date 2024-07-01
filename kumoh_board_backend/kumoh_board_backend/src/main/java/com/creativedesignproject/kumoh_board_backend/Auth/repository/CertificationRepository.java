package com.creativedesignproject.kumoh_board_backend.Auth.repository;

import org.springframework.stereotype.Repository;

import com.creativedesignproject.kumoh_board_backend.Auth.entity.Certification;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CertificationRepository {
    private final EntityManager em;

    public void save(Certification certification) {
        em.persist(certification);
    }

    public Certification findByUserEmail(String email) {
        return em.find(Certification.class, email);
    }

    public void deleteByCertificationUserEmail(String email) {
        em.createQuery("delete from Certification c where c.certificationEmail = :email")
                .setParameter("email", email)
                .executeUpdate();
    }
}
