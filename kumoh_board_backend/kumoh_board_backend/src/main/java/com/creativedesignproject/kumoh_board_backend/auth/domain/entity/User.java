package com.creativedesignproject.kumoh_board_backend.auth.domain.entity;

import com.creativedesignproject.kumoh_board_backend.common.baseentity.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "user_name", nullable = false)
    private String userId;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "profile_image")
    private String profile_image;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "certification_id")
    private Certification certification;

    @Builder
    public User(String nickname, String userId, String password, String email, String role, String profileImage, Certification certification) {
        this.nickname = nickname;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.role = role;
        this.profile_image = profileImage;
        this.certification = certification;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setProfileImage(String profileImage) {
        this.profile_image = profileImage;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}