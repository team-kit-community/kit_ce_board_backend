package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CrawlingContestsEntity extends BaseCrawlingEntity {
    private String field;
    private String host;
    private String status;
    private int views;
}
