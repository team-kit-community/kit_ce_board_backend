package com.creativedesignproject.kumoh_board_backend.auth.domain.repository.certification;

import com.creativedesignproject.kumoh_board_backend.auth.domain.entity.Certification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long>, QuerydslCertificationRepository {
    
}
