package com.creativedesignproject.kumoh_board_backend.board.domain.repository.favorite;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creativedesignproject.kumoh_board_backend.board.domain.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>, QuerydslFavoriteRepository {
    
}