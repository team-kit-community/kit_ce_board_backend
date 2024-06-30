package com.creativedesignproject.kumoh_board_backend.Auth.service.serviceimpl;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creativedesignproject.kumoh_board_backend.Auth.common.CertificationNumber;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.ChangeNicknameRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.ChangePasswordRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.EmailCertificationRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.SignInRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.SignUpRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.UserIdCheckRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.EmailCertificationResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.SignInResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.SignUpResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.UserIdCheckResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.entity.CertificationEntity;
import com.creativedesignproject.kumoh_board_backend.Auth.entity.UserEntity;
import com.creativedesignproject.kumoh_board_backend.Auth.provider.EmailProvider;
import com.creativedesignproject.kumoh_board_backend.Auth.provider.JwtProvider;
import com.creativedesignproject.kumoh_board_backend.Auth.service.AuthService;
import com.creativedesignproject.kumoh_board_backend.mapper.AuthMapper;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ChangeNicknameResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ChangePasswordResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
    private final AuthMapper authMapper;
    private final JwtProvider jwtProvider;
    private final EmailProvider emailProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try {
            String email = dto.getEmail();

            boolean isExistEmail = authMapper.existsByUserEmail(email);
            if (isExistEmail) return EmailCertificationResponseDto.duplicatedEmail();

            String certificationNumber = CertificationNumber.getCertificationNumber();

            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSuccessed) return EmailCertificationResponseDto.mailSendFail();

            CertificationEntity certificationEntity = CertificationEntity.builder()
                        .certification_email(email)
                        .certification_number(certificationNumber)
                        .build();
            
            authMapper.certificationSave(certificationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        try {
            String userId = dto.getUserId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();
            String nickName = dto.getNickName();
            
            boolean isExistNickname = authMapper.existsByUserNickname(nickName); // repository에 유저가 존재하는지 확인하고
            log.info("isExistNickname : " + isExistNickname);
            if (isExistNickname) return SignUpResponseDto.duplicateNickname();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);

            CertificationEntity certificationEntity = authMapper.findByUserEmail(email);
            if (certificationEntity == null)
                return SignUpResponseDto.certificationFail();

            boolean isMatched = certificationEntity.getCertification_email().equals(email)
                    && certificationEntity.getCertification_number().equals(certificationNumber);
            if (!isMatched)
                return SignUpResponseDto.certificationFail();

            UserEntity userEntity = UserEntity.builder()
                    .user_id(userId)
                    .password(encodedPassword)
                    .nickname(dto.getNickName())
                    .email(email)
                    .isAdmin(false)
                    .role("ROLE_USER")
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .profile_image(dto.getProfileImage())
                    .build();
            
            authMapper.UserSave(userEntity);

            authMapper.deleteByCertificationUserEmail(email); // 회원가입 하고 certification 테이블에 있는 이메일 삭제

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String token = null;
        try {
            String userId = dto.getUserId();
            UserEntity userEntity = authMapper.findByUserId(userId);
            if (userEntity == null) return SignInResponseDto.signInFail();

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) return SignInResponseDto.signInFail();

            token = jwtProvider.create(userId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignInResponseDto.success(token);
    }

    @Override
    public ResponseEntity<? super UserIdCheckResponseDto> checkUserId(UserIdCheckRequestDto dto) {
        try {
            boolean isExistUserId = authMapper.existsByUserId(dto.getUserId());
            if (isExistUserId) return UserIdCheckResponseDto.duplicateId();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return UserIdCheckResponseDto.success();
    }

    @Transactional
    @Override
    public ResponseEntity<? super ChangePasswordResponseDto> changePassword(String userId, ChangePasswordRequestDto dto) {
        try {
            UserEntity user = authMapper.findUserId(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            log.info("Updating userId: {}", userId);
            if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
                return ResponseDto.validationFail();
            }

            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            log.info("Updating user: {}", user);
            authMapper.updateUser(user);
            return ChangePasswordResponseDto.success();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super ChangeNicknameResponseDto> changeNickname(String userId,
            ChangeNicknameRequestDto dto) {
        try {
            boolean isNicknameExist = authMapper.existsByUserNickname(dto.getNewNickname());
            if (isNicknameExist) return ChangeNicknameResponseDto.duplicateNickname();

            UserEntity user = authMapper.findUserId(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            user.setNickname(dto.getNewNickname());
            authMapper.updateUser(user);
            return ChangeNicknameResponseDto.success();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}
