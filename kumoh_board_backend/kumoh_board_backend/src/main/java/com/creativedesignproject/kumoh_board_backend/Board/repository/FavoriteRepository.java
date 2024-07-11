package com.creativedesignproject.kumoh_board_backend.board.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.creativedesignproject.kumoh_board_backend.board.domain.Favorite;
import com.creativedesignproject.kumoh_board_backend.board.repository.query.FavoriteListDto;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FavoriteRepository {
    private final EntityManager em;

    public boolean findByBoardNumberAndUserId(Long userId, Long post_number) {
        return em.createQuery("select f from Favorite f where f.user.id = :userId and f.post.id = :post_number", Favorite.class)
                .setParameter("userId", userId)
                .setParameter("post_number", post_number)
                .getResultList().size() > 0;
    }

    public void save(Favorite favorite) {
        em.persist(favorite);
    }

    public void delete(Long userId, Long post_number) {
        em.createQuery("delete from Favorite f where f.user.id = :userId and f.post.id = :post_number")
                .setParameter("userId", userId)
                .setParameter("post_number", post_number)
                .executeUpdate();
    }

    public List<FavoriteListDto> findByPostId(Long postId) {
        return em.createQuery("select new com.creativedesignproject.kumoh_board_backend.Board.repository.query.FavoriteListDto(f) from Favorite f where f.post.id = :postId", FavoriteListDto.class)
                .setParameter("postId", postId)
                .getResultList();
    }
}
