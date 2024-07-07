package com.creativedesignproject.kumoh_board_backend.Auth.infrastructure;

import org.springframework.stereotype.Component;

import com.creativedesignproject.kumoh_board_backend.Auth.domain.VerificationCode;
import com.creativedesignproject.kumoh_board_backend.Auth.service.VerificationCodeProvider;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.joining;


@Component
public class VerificationCodeProviderImpl implements VerificationCodeProvider {
    @Override
    public VerificationCode provide() {
        Random random = ThreadLocalRandom.current();
        return random.ints(0, 10)
                .mapToObj(String::valueOf)
                .limit(VerificationCode.LENGTH)
                .collect(collectingAndThen(joining(), VerificationCode::new));
    }
}