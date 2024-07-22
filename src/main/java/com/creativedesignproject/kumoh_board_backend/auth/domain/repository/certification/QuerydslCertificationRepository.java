package com.creativedesignproject.kumoh_board_backend.auth.domain.repository.certification;

import com.creativedesignproject.kumoh_board_backend.auth.domain.entity.Certification;

public interface QuerydslCertificationRepository {
    Certification findByEmail(String email);
    void deleteByEmail(String email);
}
