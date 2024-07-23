package com.creativedesignproject.kumoh_board_backend.auth.service.serviceimpl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creativedesignproject.kumoh_board_backend.common.exception.BadRequestException;
import com.creativedesignproject.kumoh_board_backend.common.exception.ErrorCode;
import com.creativedesignproject.kumoh_board_backend.auth.domain.entity.Certification;
import com.creativedesignproject.kumoh_board_backend.auth.domain.entity.User;
import com.creativedesignproject.kumoh_board_backend.auth.domain.entity.VerificationCode;
import com.creativedesignproject.kumoh_board_backend.auth.domain.repository.CertificationRepository;
import com.creativedesignproject.kumoh_board_backend.auth.domain.repository.UserRepository;
import com.creativedesignproject.kumoh_board_backend.auth.dto.request.ChangeNicknameRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.request.ChangePasswordRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.request.EmailCertificationRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.request.SignInRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.request.SignUpRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.request.UserIdCheckRequestDto;
import com.creativedesignproject.kumoh_board_backend.auth.dto.response.SignInResponseDto;
import com.creativedesignproject.kumoh_board_backend.auth.provider.JwtProvider;
import com.creativedesignproject.kumoh_board_backend.auth.service.AuthService;
import com.creativedesignproject.kumoh_board_backend.auth.service.MailClient;
import com.creativedesignproject.kumoh_board_backend.auth.service.VerificationCodeProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;
    private final JwtProvider jwtProvider;
    private final MailClient mailClient;
    private final VerificationCodeProvider verificationCodeProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    @Override
    public void emailCertification(EmailCertificationRequestDto dto) {
        Certification certification = createCode(dto.getEmail());
        sendMail(certification);
        certificationRepository.save(certification);
    }

    private Certification createCode(String email) {
        VerificationCode verificationCode = verificationCodeProvider.provide();
        userRepository.findByEmail(email).ifPresent(user -> { throw new BadRequestException(ErrorCode.EMAIL_DUPLICATED); });

        Certification certification = Certification.builder()
                .email(email)
                .verificationCode(verificationCode)
                .build();

        return certification;
    }

    private void sendMail(Certification certification) {
        mailClient.sendMail(mail -> {
            mail.setTo(certification.getEmail());
            mail.setSubject("[금오 보드] 이메일 인증 코드");
            mail.setText("""
                금오 보드 이메일 인증 코드입니다.
                Code는 다음과 같습니다.
                %s
                """.formatted(certification.getVerificationCode()));
        });
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
        String profileImage = dto.getProfileImage();

        if(userRepository.findByUserId(userId) != null)
            throw new BadRequestException(ErrorCode.USER_ID_DUPLICATED);

        if (userRepository.findByNickname(nickName) != null)
            throw new BadRequestException(ErrorCode.NICKNAME_DUPLICATED);

        Certification certification = certificationRepository.findByEmail(email);

        if (certification == null) throw new BadRequestException(ErrorCode.CERTIFICATION_FAIL);

        boolean isMatched = certification.getEmail().equals(email)
                && certification.getVerificationCode().equals(certificationNumber);
        if (!isMatched) throw new BadRequestException(ErrorCode.CERTIFICATION_MISSMATCHING);

        User user = User.builder()
            .nickname(nickName)
            .userId(userId)
            .password(encodedPassword)
            .email(email)
            .role("ROLE_USER")
            .profileImage(profileImage)
            .build();
        
        userRepository.save(user);
        certificationRepository.deleteByEmail(email);
    }

    @Override
    public SignInResponseDto signIn(SignInRequestDto dto) {
        String userId = dto.getUserId();
        User user = userRepository.findByUserId(userId);
        if (user == null) throw new BadRequestException(ErrorCode.USER_NOT_FOUND);

        String password = dto.getPassword();
        String encodedPassword = user.getPassword();
        boolean isMatched = passwordEncoder.matches(password, encodedPassword);
        if (!isMatched) throw new BadRequestException(ErrorCode.PASSWORD_NOT_MATCHED);

        String token = jwtProvider.create(userId);
        return SignInResponseDto.builder()
                .token(token)
                .build();
    }

    @Override
    public void checkUserId(UserIdCheckRequestDto dto) {
        if (userRepository.findByUserId(dto.getUserId()) != null)
            throw new BadRequestException(ErrorCode.USER_ID_DUPLICATED);
    }

    @Transactional
    @Override
    public void changePassword(String userId, ChangePasswordRequestDto dto) {
        User user = userRepository.findByUserId(userId);
        if (user == null) throw new BadRequestException(ErrorCode.USER_NOT_FOUND);

        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new BadRequestException(ErrorCode.PASSWORD_NOT_MATCHED);
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
    }

    @Transactional
    @Override
    public void changeNickname(String userId, ChangeNicknameRequestDto dto) {
        User nickname = userRepository.findByNickname(dto.getNewNickname());
        if (nickname != null) throw new BadRequestException(ErrorCode.NICKNAME_DUPLICATED);

        User user = userRepository.findByUserId(userId);
        if (user == null) throw new BadRequestException(ErrorCode.USER_NOT_FOUND);

        user.setNickname(dto.getNewNickname());
    }
}
