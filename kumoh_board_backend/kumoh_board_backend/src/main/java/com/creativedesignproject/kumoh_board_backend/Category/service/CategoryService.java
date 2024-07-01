package com.creativedesignproject.kumoh_board_backend.Category.service;

import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Category.dto.request.UpdateCategoryRequestDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.response.RegisterCategoryResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.entity.Category;
import com.creativedesignproject.kumoh_board_backend.Category.dto.response.UpdateCategoryResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.response.DeleteCategoryResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.response.GetCategoryListResponseDto;

public interface CategoryService {
    ResponseEntity<? super RegisterCategoryResponseDto> registerCategory(Category categoryEntity);
    ResponseEntity<? super UpdateCategoryResponseDto> updateCategory(Long category_id, UpdateCategoryRequestDto dto);
    ResponseEntity<? super DeleteCategoryResponseDto> deleteCategoryName(Long category_id);
    ResponseEntity<? super GetCategoryListResponseDto> getCategoryList();
}
