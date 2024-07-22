package com.creativedesignproject.kumoh_board_backend.board.domain.repository.comment;

import java.util.List;

import static com.creativedesignproject.kumoh_board_backend.board.domain.entity.QComment.comment;

import com.creativedesignproject.kumoh_board_backend.board.domain.entity.Comment;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements QuerydslCommentRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Comment> findByPostId(Long post_number) {
        return queryFactory
            .selectFrom(comment)
            .where(comment.post.id.eq(post_number))
            .fetch();
    }
}
