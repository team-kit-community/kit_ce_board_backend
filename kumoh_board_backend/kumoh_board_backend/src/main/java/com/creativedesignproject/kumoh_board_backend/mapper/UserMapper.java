package com.creativedesignproject.kumoh_board_backend.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.creativedesignproject.kumoh_board_backend.Auth.entity.UserEntity;

@Mapper
public interface UserMapper {
    public UserEntity findByUserId(String userId);
}
