package com.creativedesignproject.kumoh_board_backend.board.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.creativedesignproject.kumoh_board_backend.board.domain.entity.Favorite;
import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.FavoriteListDto;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existsByUserIdAndPostId(Long userId, Long post_number);
    Optional<Favorite> findByBoardNumberAndUserId(Long userId, Long post_number);

    void delete(Long userId, Long post_number);

    @Query("SELECT new com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.FavoriteListDto(f) FROM Favorite f WHERE f.postId = :postId")
    List<FavoriteListDto> findByPostId(Long postId);
}
