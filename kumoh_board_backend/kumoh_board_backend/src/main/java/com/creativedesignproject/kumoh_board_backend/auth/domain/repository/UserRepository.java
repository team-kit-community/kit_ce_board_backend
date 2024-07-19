package com.creativedesignproject.kumoh_board_backend.auth.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creativedesignproject.kumoh_board_backend.auth.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    User findByNickname(String nickName);
    boolean existsByUserId(String userId);
    User findByUserId(String userId);
}