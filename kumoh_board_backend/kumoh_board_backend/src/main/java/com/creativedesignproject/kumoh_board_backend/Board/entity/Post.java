package com.creativedesignproject.kumoh_board_backend.Board.entity;

import lombok.Builder;
import lombok.Getter;

import com.creativedesignproject.kumoh_board_backend.Auth.domain.User;
import com.creativedesignproject.kumoh_board_backend.Category.entity.Category;
import com.creativedesignproject.kumoh_board_backend.Common.BaseEntity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Getter
public class Post extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "contents", nullable = false)
    private String contents;
    
    @Column(name = "favorite_count")
    private int favorite_count = 0;
    
    @Column(name = "comment_count")
    private int comment_count = 0;
    
    @Column(name = "view_count")
    private int view_count = 0;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public void increaseViewCount() {
        this.view_count++;
    }

    public void increaseCommentCount() {
        this.comment_count++;
    }

    public void increaseFavoriteCount() {
        this.favorite_count++;
    }

    public void decreaseFavoriteCount() {
        this.favorite_count--;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    @Builder
    public Post(String title, String contents, User user, Category category) {
        this.title = title;
        this.contents = contents;
        this.user = user;
        this.category = category;
    }
}