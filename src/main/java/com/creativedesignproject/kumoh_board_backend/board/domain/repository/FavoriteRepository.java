package com.creativedesignproject.kumoh_board_backend.board.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.creativedesignproject.kumoh_board_backend.board.domain.entity.Favorite;
import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.FavoriteListDto;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long post_number);

    Optional<Favorite> findByPostIdAndUserId(Long userId, Long post_number);

    void delete(@Param("userId") Long userId, @Param("postId") Long postId);

    List<FavoriteListDto> findByPostId(@Param("postId") Long postId);
}