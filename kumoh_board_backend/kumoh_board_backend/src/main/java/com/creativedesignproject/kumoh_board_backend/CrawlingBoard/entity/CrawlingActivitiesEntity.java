package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CrawlingActivitiesEntity extends BaseCrawlingEntity {
    private String field;
    private String image;
}