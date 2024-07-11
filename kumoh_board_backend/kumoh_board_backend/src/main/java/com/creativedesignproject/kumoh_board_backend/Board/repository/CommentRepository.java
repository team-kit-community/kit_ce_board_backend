package com.creativedesignproject.kumoh_board_backend.board.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.creativedesignproject.kumoh_board_backend.board.domain.Comment;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManager em;

    public void save(Comment comment) {
        em.persist(comment);
    }

    public List<Comment> selectCommentsByPostNumber(Long post_number) {
        return em.createQuery("select c from Comment c where c.post.id = :post_number", Comment.class)
                .setParameter("post_number", post_number)
                .getResultList();
    }

    public Comment findById(Long id) {
        return em.find(Comment.class, id);
    }
}
