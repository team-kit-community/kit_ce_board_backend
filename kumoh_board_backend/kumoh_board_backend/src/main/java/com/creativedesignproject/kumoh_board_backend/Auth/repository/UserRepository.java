package com.creativedesignproject.kumoh_board_backend.Auth.repository;

import org.springframework.stereotype.Repository;
import java.util.List;

import com.creativedesignproject.kumoh_board_backend.Auth.domain.User;

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

    public User findByUserEmail(String email) {
        List<User> users = em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    public User findByUserNickname(String nickName) {
        return em.createQuery("select u from User u where u.nickName = :nickName", User.class)
                .setParameter("nickName", nickName)
                .getSingleResult();
    }

    public boolean existsByUserId(String userId) {
        return em.createQuery("select count(u) from User u where u.user_id = :userId", Long.class)
                .setParameter("userId", userId)
                .getSingleResult() > 0;
    }

    public User findByUserId(String userId) {
        return em.createQuery("select u from User u where u.userId = :userId", User.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    public void save(User user) {
        em.persist(user);
    }
}
