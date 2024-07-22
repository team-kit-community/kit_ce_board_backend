package com.creativedesignproject.kumoh_board_backend.auth.domain.repository.user;

import java.util.Optional;

import com.creativedesignproject.kumoh_board_backend.auth.domain.entity.User;

public interface QuerydslUserRepository {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    User findByNickname(String nickName);
    boolean existsByUserId(String userId);
    User findByUserId(String userId);
}
