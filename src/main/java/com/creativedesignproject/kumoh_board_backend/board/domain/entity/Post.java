package com.creativedesignproject.kumoh_board_backend.board.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.creativedesignproject.kumoh_board_backend.auth.domain.entity.User;
import com.creativedesignproject.kumoh_board_backend.category.domain.entity.Category;
import com.creativedesignproject.kumoh_board_backend.common.baseentity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "contents", nullable = false)
    private String contents;
    
    @Column(name = "favorite_count", nullable = false)
    private int favorite_count = 0;
    
    @Column(name = "comment_count", nullable = false)
    private int comment_count = 0;
    
    @Column(name = "view_count", nullable = false)
    private int view_count = 0;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

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
    public Post(String title, String contents, User user, Category category, List<Image> images) {
        this.title = title;
        this.contents = contents;
        this.user = user;
        this.category = category;
        this.images = images;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}