package com.creativedesignproject.kumoh_board_backend.crawlingboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CrawlingContests extends BaseCrawling {
    @Column(name = "field")
    private String field;
    @Column(name = "host")
    private String host;
    @Column(name = "status")
    private String status;
    @Column(name = "views")
    private int views;

    @Builder
    public CrawlingContests(String title, String date, String url, String detailData, String field, String host, String status, int views) {
        super(title, date, url, detailData);
        this.field = field;
        this.host = host;
        this.status = status;
        this.views = views;
    }
}
