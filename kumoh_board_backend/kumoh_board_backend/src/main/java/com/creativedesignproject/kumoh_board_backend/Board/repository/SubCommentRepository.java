package com.creativedesignproject.kumoh_board_backend.board.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.creativedesignproject.kumoh_board_backend.board.domain.SubComment;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SubCommentRepository {
    private final EntityManager em;

    public List<SubComment> findByParentCommentId(Long parentCommentId) {
        return em.createQuery("select s from SubComment s where s.parentComment.id = :parentCommentId", SubComment.class)
                .setParameter("parentCommentId", parentCommentId)
                .getResultList();
    }

    public void save(SubComment subComment) {
        em.persist(subComment);
    }
}