package com.creativedesignproject.kumoh_board_backend.User.service.Impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creativedesignproject.kumoh_board_backend.Auth.domain.User;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.repository.UserRepository;
import com.creativedesignproject.kumoh_board_backend.User.dto.response.GetSignInUserResponseDto;
import com.creativedesignproject.kumoh_board_backend.User.service.UserService;
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
