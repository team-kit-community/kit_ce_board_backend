package com.creativedesignproject.kumoh_board_backend.auth.domain.repository.user;

import java.util.Optional;

import com.creativedesignproject.kumoh_board_backend.auth.domain.entity.QUser;
import com.creativedesignproject.kumoh_board_backend.auth.domain.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements QuerydslUserRepository {
    private final JPAQueryFactory queryFactory;
    private final QUser user = QUser.user;

    @Override
    public boolean existsByEmail(String email) {
        return queryFactory
                .selectFrom(user)
                .where(user.email.eq(email))
                .fetchFirst() != null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(
            queryFactory
                .selectFrom(user)
                .where(user.email.eq(email))
                .fetchOne()
        );
    }

    @Override
    public User findByNickname(String nickName) {
        return queryFactory
                .selectFrom(user)
                .where(user.nickname.eq(nickName))
                .fetchOne();
    }

    @Override
    public boolean existsByUserId(String userId) {
        return queryFactory
                .selectFrom(user)
                .where(user.userId.eq(userId))
                .fetchFirst() != null;
    }

    @Override
    public User findByUserId(String userId) {
        return queryFactory
                .selectFrom(user)
                .where(user.userId.eq(userId))
                .fetchOne();
    }
    
}
