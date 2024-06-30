package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseTimeEntity {
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}