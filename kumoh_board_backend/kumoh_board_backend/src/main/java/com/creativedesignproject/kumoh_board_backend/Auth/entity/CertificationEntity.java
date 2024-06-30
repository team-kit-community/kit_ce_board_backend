package com.creativedesignproject.kumoh_board_backend.Auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CertificationEntity {
    private String certification_number;
    private String certification_email;
}