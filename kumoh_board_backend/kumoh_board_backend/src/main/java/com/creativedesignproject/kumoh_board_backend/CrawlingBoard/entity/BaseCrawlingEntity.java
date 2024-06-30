package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseCrawlingEntity {
    private int id;
    private String title;
    private LocalDate date;
    private String url;
    private String detailData;

    public void setDetailData(String detailData) {
        this.detailData = detailData;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}