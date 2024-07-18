package com.creativedesignproject.kumoh_board_backend.crawlingboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LinkCareerActivities extends BaseCrawling {
    @Column(name = "image")
    private String image;
    @Column(name = "host")
    private String host;
    @Column(name = "views")
    private int views;
    @Column(name = "comments")
    private int comments;

    @Builder
    public LinkCareerActivities(String title, String date, String url, String detailData, String image, String host, int views, int comments) {
        super(title, date, url, detailData);
        this.image = image;
        this.host = host;
        this.views = views;
        this.comments = comments;
    }
}