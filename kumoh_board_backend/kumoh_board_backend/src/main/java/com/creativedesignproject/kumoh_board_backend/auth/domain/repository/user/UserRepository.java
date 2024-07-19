package com.creativedesignproject.kumoh_board_backend.auth.domain.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creativedesignproject.kumoh_board_backend.auth.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, QuerydslUserRepository {

}