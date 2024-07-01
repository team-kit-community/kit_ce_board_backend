package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class CrawlingActivities extends BaseCrawling {
    @Column(name = "field")
    private String field;
    @Column(name = "image")
    private String image;

    @Builder
    public CrawlingActivities(String title, String date, String url, String detailData, String field, String image) {
        super(title, date, url, detailData);
        this.field = field;
        this.image = image;
    }
}