package com.creativedesignproject.kumoh_board_backend.Auth.repository;

import org.springframework.stereotype.Repository;

import com.creativedesignproject.kumoh_board_backend.Auth.entity.User;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public boolean existsByUserEmail(String email) {
        return em.createQuery("select count(u) from User u where u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult() > 0;
    }

    public boolean existsByUserNickname(String nickName) {
        return em.createQuery("select count(u) from User u where u.nickName = :nickName", Long.class)
                .setParameter("nickName", nickName)
                .getSingleResult() > 0;
    }

    public boolean existsByUserId(String userId) {
        return em.createQuery("select count(u) from User u where u.user_id = :userId", Long.class)
                .setParameter("userId", userId)
                .getSingleResult() > 0;
    }

    public User findByUserId(String userId) {
        return em.find(User.class, userId);
    }

    public void save(User user) {
        em.persist(user);
    }
}
