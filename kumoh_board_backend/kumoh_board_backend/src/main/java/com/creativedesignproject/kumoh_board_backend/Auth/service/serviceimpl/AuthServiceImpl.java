package com.creativedesignproject.kumoh_board_backend.Auth.service.serviceimpl;

import org.springframework.http.ResponseEntity;
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
import com.creativedesignproject.kumoh_board_backend.Auth.entity.Certification;
import com.creativedesignproject.kumoh_board_backend.Auth.entity.User;
import com.creativedesignproject.kumoh_board_backend.Auth.provider.EmailProvider;
import com.creativedesignproject.kumoh_board_backend.Auth.provider.JwtProvider;
import com.creativedesignproject.kumoh_board_backend.Auth.repository.CertificationRepository;
import com.creativedesignproject.kumoh_board_backend.Auth.repository.UserRepository;
import com.creativedesignproject.kumoh_board_backend.Auth.service.AuthService;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ChangeNicknameResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ChangePasswordResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;
    private final JwtProvider jwtProvider;
    private final EmailProvider emailProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try {
            String email = dto.getEmail();

            boolean isExistEmail = userRepository.existsByUserEmail(email);
            if (isExistEmail) return EmailCertificationResponseDto.duplicatedEmail();

            String certificationNumber = CertificationNumber.getCertificationNumber();

            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSuccessed) return EmailCertificationResponseDto.mailSendFail();

            Certification certification = Certification.builder()
                        .certificationEmail(email)
                        .certificationNumber(certificationNumber)
                        .build();
            
            certificationRepository.save(certification);

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
            
            boolean isExistNickname = userRepository.existsByUserNickname(nickName);
            if (isExistNickname) return SignUpResponseDto.duplicateNickname();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);

            Certification certification = certificationRepository.findByUserEmail(email);
            if (certification == null)
                return SignUpResponseDto.certificationFail();

            boolean isMatched = certification.getCertificationEmail().equals(email)
                    && certification.getCertificationNumber().equals(certificationNumber);
            if (!isMatched)
                return SignUpResponseDto.certificationFail();

            User user = User.builder()
                    .userId(userId)
                    .password(encodedPassword)
                    .nickname(dto.getNickName())
                    .email(email)
                    .role("ROLE_USER")
                    .profileImage(dto.getProfileImage())
                    .build();
            
            userRepository.save(user);

            certificationRepository.deleteByCertificationUserEmail(email);

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
            User user = userRepository.findByUserId(userId);
            if (user == null) return SignInResponseDto.signInFail();

            String password = dto.getPassword();
            String encodedPassword = user.getPassword();
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
            boolean isExistUserId = userRepository.existsByUserId(dto.getUserId());
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
            User user = userRepository.findByUserId(userId);
            if (user == null) return ResponseDto.validationFail();

            if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
                return ResponseDto.validationFail();
            }

            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            userRepository.save(user);
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
            boolean isNicknameExist = userRepository.existsByUserNickname(dto.getNewNickname());
            if (isNicknameExist) return ChangeNicknameResponseDto.duplicateNickname();

            User user = userRepository.findByUserId(userId);
            if (user == null) return ResponseDto.validationFail();

            user.setNickname(dto.getNewNickname());
            userRepository.save(user);
            return ChangeNicknameResponseDto.success();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}
