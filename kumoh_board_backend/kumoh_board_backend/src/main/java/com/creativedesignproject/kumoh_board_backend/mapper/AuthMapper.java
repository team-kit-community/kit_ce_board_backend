package com.creativedesignproject.kumoh_board_backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.creativedesignproject.kumoh_board_backend.Auth.entity.CertificationEntity;
import com.creativedesignproject.kumoh_board_backend.Auth.entity.UserEntity;

import java.util.Optional;

@Mapper
public interface AuthMapper {
    public boolean existsByUserNickname(String nickName);
    public boolean existsByUserId(String userId);
    public boolean existsByUserEmail(String email);
    public int certificationSave(CertificationEntity certificationEntity);
    public CertificationEntity findByUserEmail(String email);
    public int UserSave(UserEntity entity);
    public int deleteByCertificationUserEmail(String email);
    public UserEntity findByUserId(String userId);
    public Optional<UserEntity> findUserId(@Param("userId") String userId);
    public void updateUser(UserEntity user);
}