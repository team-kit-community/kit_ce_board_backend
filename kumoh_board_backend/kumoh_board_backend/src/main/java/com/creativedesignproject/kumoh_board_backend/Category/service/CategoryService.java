package com.creativedesignproject.kumoh_board_backend.category.service;

import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.category.domain.Category;
import com.creativedesignproject.kumoh_board_backend.category.dto.request.UpdateCategoryRequestDto;
import com.creativedesignproject.kumoh_board_backend.category.dto.response.DeleteCategoryResponseDto;
import com.creativedesignproject.kumoh_board_backend.category.dto.response.GetCategoryListResponseDto;
import com.creativedesignproject.kumoh_board_backend.category.dto.response.RegisterCategoryResponseDto;
import com.creativedesignproject.kumoh_board_backend.category.dto.response.UpdateCategoryResponseDto;

public interface CategoryService {
    ResponseEntity<? super RegisterCategoryResponseDto> registerCategory(Category categoryEntity);
    ResponseEntity<? super UpdateCategoryResponseDto> updateCategory(Long category_id, UpdateCategoryRequestDto dto);
    ResponseEntity<? super DeleteCategoryResponseDto> deleteCategoryName(Long category_id);
    ResponseEntity<? super GetCategoryListResponseDto> getCategoryList();
}
