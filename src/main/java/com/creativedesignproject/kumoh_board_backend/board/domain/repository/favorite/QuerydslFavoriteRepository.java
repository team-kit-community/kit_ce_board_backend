package com.creativedesignproject.kumoh_board_backend.board.domain.repository.favorite;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.creativedesignproject.kumoh_board_backend.board.domain.entity.Favorite;
import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.FavoriteListDto;

import java.util.List;
import java.util.Optional;

public interface QuerydslFavoriteRepository {
    boolean existsByUserIdAndPostId(Long userId, Long post_number);
    Optional<Favorite> findByPostIdAndUserId(Long userId, Long post_number);

    @Query("DELETE FROM Favorite f WHERE f.user.id = :userId AND f.post.id = :postId")
    void delete(@Param("userId") Long userId, @Param("postId") Long postId);

    @Query("SELECT new com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.FavoriteListDto(f) FROM Favorite f WHERE f.post.id = :postId")
    List<FavoriteListDto> findByPostId(@Param("postId") Long postId);
}
