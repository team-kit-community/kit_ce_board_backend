package com.creativedesignproject.kumoh_board_backend.Auth.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {
    private int id;
    private String nickname;
    private String user_id;
    private String password;
    private String email;
    private boolean isAdmin;
    private String role;
    private Date createdAt;
    private Date updatedAt;
    private String profile_image;
}