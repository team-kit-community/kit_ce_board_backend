package com.creativedesignproject.kumoh_board_backend.Search.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchEntity {
    private int sequence;
    private String search_word;
    private String relation_word;
    private boolean relation;
}
