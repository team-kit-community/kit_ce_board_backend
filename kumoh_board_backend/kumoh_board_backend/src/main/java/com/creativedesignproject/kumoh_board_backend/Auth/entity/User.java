package com.creativedesignproject.kumoh_board_backend.Auth.entity;

import com.creativedesignproject.kumoh_board_backend.Common.BaseEntity.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class User extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "user_name", nullable = false)
    private String user_id;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "profile_image")
    private String profile_image;

    @Builder
    public User(String nickname, String userId, String password, String email, String role, String profileImage) {
        this.nickname = nickname;
        this.user_id = userId;
        this.password = password;
        this.email = email;
        this.role = role;
        this.profile_image = profileImage;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}