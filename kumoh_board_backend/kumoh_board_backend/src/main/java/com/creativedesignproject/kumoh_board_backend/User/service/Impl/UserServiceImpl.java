package com.creativedesignproject.kumoh_board_backend.User.service.Impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.entity.UserEntity;
import com.creativedesignproject.kumoh_board_backend.User.dto.response.GetSignInUserResponseDto;
import com.creativedesignproject.kumoh_board_backend.User.service.UserService;
import com.creativedesignproject.kumoh_board_backend.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId) {
        UserEntity userEntity = null;
        
        try {
            userEntity = userMapper.findByUserId(userId);
            if(userEntity == null) return GetSignInUserResponseDto.notExistUser();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(userEntity);
    }

    
}
