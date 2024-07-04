package com.creativedesignproject.kumoh_board_backend.Auth.service.serviceimpl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creativedesignproject.kumoh_board_backend.Auth.common.CertificationNumber;
import com.creativedesignproject.kumoh_board_backend.Auth.domain.Certification;
import com.creativedesignproject.kumoh_board_backend.Auth.domain.User;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.ChangeNicknameRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.ChangePasswordRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.EmailCertificationRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.SignInRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.SignUpRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.request.UserIdCheckRequestDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.SignInResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.UserIdCheckResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.provider.EmailProvider;
import com.creativedesignproject.kumoh_board_backend.Auth.provider.JwtProvider;
import com.creativedesignproject.kumoh_board_backend.Auth.repository.CertificationRepository;
import com.creativedesignproject.kumoh_board_backend.Auth.repository.UserRepository;
import com.creativedesignproject.kumoh_board_backend.Auth.service.AuthService;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ChangeNicknameResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ChangePasswordResponseDto;
import com.creativedesignproject.kumoh_board_backend.Common.exception.BadRequestException;
import com.creativedesignproject.kumoh_board_backend.Common.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;
    private final JwtProvider jwtProvider;
    private final EmailProvider emailProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    @Override
    public void emailCertification(EmailCertificationRequestDto dto) {
        String email = dto.getEmail();
        
        User user = userRepository.findByUserEmail(email);

        if(user != null) throw new BadRequestException(ErrorCode.EMAIL_DUPLICATED);

        String certificationNumber = CertificationNumber.getCertificationNumber();

        boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
        if (!isSuccessed) throw new BadRequestException(ErrorCode.EMAIL_SEND_FAIL);

        user = User.builder()
                .email(email)
                .certification(Certification.builder()
                        .certificationNumber(certificationNumber)
                        .build())
                .build();

        Certification certification = Certification.builder()
                .certificationNumber(certificationNumber)
                .build();
        
        certificationRepository.save(certification);
    }

    @Transactional
    @Override
    public void signUp(SignUpRequestDto dto) {
        String userId = dto.getUserId();
        String email = dto.getEmail();
        String certificationNumber = dto.getCertificationNumber();
        String nickName = dto.getNickName();
        String password = dto.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        
        User user = userRepository.findByUserId(userId);

        if(user != null) throw new BadRequestException(ErrorCode.USER_ID_DUPLICATED);

        if (userRepository.findByUserNickname(nickName) != null)
            throw new BadRequestException(ErrorCode.NICKNAME_DUPLICATED);

        User userMail = userRepository.findByUserEmail(email);

        if (userMail == null) throw new BadRequestException(ErrorCode.CERTIFICATION_FAIL);

        boolean isMatched = userMail.getEmail().equals(email)
                && userMail.getCertification().getCertificationNumber().equals(certificationNumber);
        if (!isMatched) throw new BadRequestException(ErrorCode.CERTIFICATION_MISSMATCHING);

        // User user = User.builder()
        //         .userId(userId)
        //         .password(encodedPassword)
        //         .nickname(dto.getNickName())
        //         .email(email)
        //         .role("ROLE_USER")
        //         .profileImage(dto.getProfileImage())
        //         .build();
        
        userRepository.save(user);
        certificationRepository.deleteByCertificationUserEmail(email);
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
            User userNickname = userRepository.findByUserNickname(dto.getNewNickname());
            if (userNickname != null) throw new BadRequestException(ErrorCode.NICKNAME_DUPLICATED);

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
