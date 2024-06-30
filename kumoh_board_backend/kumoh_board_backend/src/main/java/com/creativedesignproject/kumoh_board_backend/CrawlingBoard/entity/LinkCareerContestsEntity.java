package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LinkCareerContestsEntity extends BaseCrawlingEntity {
    private String image;
    private String host;
    private int views;
    private int comments;
}
