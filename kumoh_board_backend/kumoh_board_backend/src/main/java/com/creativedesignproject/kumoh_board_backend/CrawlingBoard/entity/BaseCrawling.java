package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity;

import com.creativedesignproject.kumoh_board_backend.Common.BaseEntity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public abstract class BaseCrawling extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "date", nullable = false)
    private String date;
    
    @Column(name = "url", nullable = false)
    private String url;
    
    @Column(name = "detail_data", nullable = false)
    private String detailData;

    public void setDetailData(String detailData) {
        this.detailData = detailData;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    protected BaseCrawling() {}

    public BaseCrawling(String title, String date, String url, String detailData) {
        this.title = title;
        this.date = date;
        this.url = url;
        this.detailData = detailData;
    }
}