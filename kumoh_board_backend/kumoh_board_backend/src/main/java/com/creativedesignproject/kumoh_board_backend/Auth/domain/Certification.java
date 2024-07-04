package com.creativedesignproject.kumoh_board_backend.Auth.domain;

import com.creativedesignproject.kumoh_board_backend.Common.BaseEntity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Certification extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certification_id")
    private Long id;

    @Column(name = "certification_number", nullable = false)
    private String certificationNumber;

    @Builder
    public Certification(String certificationNumber) {
        this.certificationNumber = certificationNumber;
    }
}