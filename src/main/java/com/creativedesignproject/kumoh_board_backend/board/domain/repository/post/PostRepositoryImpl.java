package com.creativedesignproject.kumoh_board_backend.board.domain.repository.post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Collections;

import static com.creativedesignproject.kumoh_board_backend.board.domain.entity.QPost.post;

import com.creativedesignproject.kumoh_board_backend.board.domain.entity.Post;
import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.PostDto;
import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.QCategoryPostDto;
import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.QImageDto;
import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.QPostDto;
import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.QUserDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostRepositoryImpl implements QuerydslPostRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Post> selectBoardWithImage(Long categoryId, Long postId) {
        return Optional.ofNullable(
            queryFactory
            .selectFrom(post)
            .leftJoin(post.images).fetchJoin()
            .where(post.category.id.eq(categoryId)
                .and(post.id.eq(postId)))
            .fetchOne()
        );
    }

    @Override
    public boolean isOwner(Long categoryId, Long postId, String userId) {
        return queryFactory
            .selectOne()
            .from(post)
            .where(post.category.id.eq(categoryId)
                .and(post.id.eq(postId))
                .and(post.user.userId.eq(userId)))
            .fetchFirst() != null;
    }

    @Override
    public List<PostDto> selectLatestBoardList(Long category_id, LocalDateTime sevenDaysAgoDate) {
        // return queryFactory
        //     .select(new QPostDto(
        //         post.title,
        //         post.contents,
        //         post.favorite_count,
        //         post.comment_count,
        //         post.view_count,
        //         new QUserDto(post.user.nickname, post.user.profile_image),
        //         new QCategoryPostDto(post.category.name, ),
        //         post.updatedDate,
        //         list(
        //             new QImageDto(post.images.url)
        //         )
        //     )
        //     .from(post)
        //     .leftJoin(post.user)
        //     .leftJoin(post.category)
        //     .leftJoin(post.images)
        //     .where(post.category.id.eq(category_id)
        //         .and(post.updatedDate.goe(sevenDaysAgoDate)))
        //     .orderBy(post.updatedDate.desc())
        //     .fetch();
        return null;
    }

    @Override
    public List<PostDto> selectTop3BoardList(LocalDateTime sevenDaysAgoDate, Long category_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectTop3BoardList'");
    }

    @Override
    public List<PostDto> selectSearchBoardList(String searchWord, String relationWord) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectSearchBoardList'");
    }

    @Override
    public List<PostDto> selectUserBoardList(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectUserBoardList'");
    }

}
