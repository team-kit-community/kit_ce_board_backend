package com.creativedesignproject.kumoh_board_backend.CrawlingBoard.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity extends BaseTimeEntity {
    private String createdBy;
    private String lastModifiedBy;
}
