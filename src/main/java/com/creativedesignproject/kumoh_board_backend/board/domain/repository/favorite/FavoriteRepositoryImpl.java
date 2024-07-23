package com.creativedesignproject.kumoh_board_backend.board.domain.repository.favorite;

import static com.creativedesignproject.kumoh_board_backend.board.domain.entity.QFavorite.favorite;
import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.FavoriteListDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FavoriteRepositoryImpl implements QuerydslFavoriteRepository {
    private final JPAQueryFactory queryFactory;

    // @Override
    // public boolean existsByUserIdAndPostId(Long userId, Long post_number) {
    //     return queryFactory
    //         .selectFrom(favorite)
    //         .where(favorite.user.id.eq(userId)
    //         .and(favorite.post.id.eq(post_number)))
    //         .fetchFirst() != null;
    // }

    // @Override
    // public Optional<Favorite> findByPostIdAndUserId(Long userId, Long post_number) {
    //     return Optional.ofNullable(
    //         queryFactory
    //         .selectFrom(favorite)
    //         .where(favorite.user.id.eq(userId)
    //                 .and(favorite.post.id.eq(post_number)))
    //         .fetchOne()
    //     );
    // }

    @Override
    public void delete(Long userId, Long postId) {
        queryFactory
            .delete(favorite)
            .where(favorite.user.id.eq(userId)
                    .and(favorite.post.id.eq(postId)))
            .execute();
    }

    @Override
    public List<FavoriteListDto> findByPostId(Long postId) {
        return queryFactory
            .select(Projections.constructor(FavoriteListDto.class,
                    favorite.user.userId))
            .from(favorite)
            .where(favorite.post.id.eq(postId))
            .fetch();
    }
}
