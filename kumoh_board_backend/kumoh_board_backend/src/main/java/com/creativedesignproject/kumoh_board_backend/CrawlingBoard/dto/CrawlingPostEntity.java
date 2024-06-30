package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrawlingPostEntity {
    private String title;
    private LocalDate date;
    private String detailData;
}
