package com.creativedesignproject.kumoh_board_backend.Board.entity;

import java.util.ArrayList;
import java.util.List;

import com.creativedesignproject.kumoh_board_backend.Auth.entity.User;
import com.creativedesignproject.kumoh_board_backend.Common.BaseEntity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Comment extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "contents", nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @JsonIgnore
    @OneToMany(mappedBy = "parentComment")
    private List<SubComment> subcomments = new ArrayList<>();

    @Builder
    public Comment(String contents, User user, Post post) {
        this.contents = contents;
        this.user = user;
        this.post = post;
    }

    public List<SubComment> getSubcomments() {
        return subcomments;
    }
}
