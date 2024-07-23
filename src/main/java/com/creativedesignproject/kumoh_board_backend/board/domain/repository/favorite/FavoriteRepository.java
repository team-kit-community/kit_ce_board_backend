package com.creativedesignproject.kumoh_board_backend.board.domain.repository.favorite;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creativedesignproject.kumoh_board_backend.board.domain.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>, QuerydslFavoriteRepository {
    boolean existsByUserIdAndPostId(Long userId, Long post_number);

    Optional<Favorite> findByPostIdAndUserId(Long userId, Long post_number);
}