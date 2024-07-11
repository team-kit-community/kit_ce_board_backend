package com.creativedesignproject.kumoh_board_backend.crawlingboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
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
