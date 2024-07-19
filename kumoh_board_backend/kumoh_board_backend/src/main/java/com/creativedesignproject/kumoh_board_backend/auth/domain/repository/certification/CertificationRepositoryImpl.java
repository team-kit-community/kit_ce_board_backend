package com.creativedesignproject.kumoh_board_backend.auth.domain.repository.certification;

import static com.creativedesignproject.kumoh_board_backend.auth.domain.entity.QCertification.certification;

import com.creativedesignproject.kumoh_board_backend.auth.domain.entity.Certification;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CertificationRepositoryImpl implements QuerydslCertificationRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Certification findByEmail(String email) {
        return queryFactory
                .selectFrom(certification)
                .where(certification.email.eq(email))
                .fetchOne();
    }

    @Override
    public void deleteByEmail(String email) {
        queryFactory
            .delete(certification)
            .where(certification.email.eq(email))
            .execute();
    }
}
