package com.creativedesignproject.kumoh_board_backend.user.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creativedesignproject.kumoh_board_backend.auth.domain.User;
import com.creativedesignproject.kumoh_board_backend.auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.auth.repository.UserRepository;
import com.creativedesignproject.kumoh_board_backend.user.dto.response.GetSignInUserResponseDto;
import com.creativedesignproject.kumoh_board_backend.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId) {
        User user = null;
        
        try {
            user = userRepository.findByUserId(userId);
            if(user == null) return GetSignInUserResponseDto.notExistUser();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(user);
    }
}
