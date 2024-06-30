package com.creativedesignproject.kumoh_board_backend.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.creativedesignproject.kumoh_board_backend.Search.entity.SearchEntity;

@Mapper
public interface SearchMapper {
    //검색 로그 저장
    public void searchLogSave(SearchEntity searchEntity);
}
