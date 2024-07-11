package com.creativedesignproject.kumoh_board_backend.crawlingboard.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CrawlingPostDto {
    private String title;
    private String date;
    private String detailData;

    public CrawlingPostDto() {
    }

    @Builder
    public CrawlingPostDto(String title, String date, String detailData) {
        this.title = title;
        this.date = date;
        this.detailData = detailData;
    }
}
