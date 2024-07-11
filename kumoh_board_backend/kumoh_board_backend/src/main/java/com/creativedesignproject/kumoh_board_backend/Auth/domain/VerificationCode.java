package com.creativedesignproject.kumoh_board_backend.auth.domain;

import java.util.regex.Pattern;

import com.creativedesignproject.kumoh_board_backend.common.exception.ValidException;
import com.creativedesignproject.kumoh_board_backend.common.util.Validator;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerificationCode {
    public static final int LENGTH = 6;
    private static final Pattern POSITIVE_REGEX = Pattern.compile("^\\d+$");

    @Column(name = "code")
    private String value;

    public VerificationCode(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        validateBlank(value);
        validateLength(value);
        validatePositive(value);
    }

    private void validateBlank(String value) {
        Validator.notBlank(value, "VerificationCode");
    }

    private void validateLength(String value) {
        if (value.length() != LENGTH) {
            throw new ValidException("VerificationCode의 길이는 %d 이어야 합니다.".formatted(LENGTH));
        }
    }

    private void validatePositive(String value) {
        if (!POSITIVE_REGEX.matcher(value).matches()) {
            throw new ValidException("VerificationCode는 0~9의 양수 형식이어야 합니다.");
        }
    }
}
