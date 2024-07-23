package com.creativedesignproject.kumoh_board_backend.board.domain.repository.favorite;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.FavoriteListDto;

public interface QuerydslFavoriteRepository {
    void delete(@Param("userId") Long userId, @Param("postId") Long postId);

    List<FavoriteListDto> findByPostId(@Param("postId") Long postId);
}
