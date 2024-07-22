package com.creativedesignproject.kumoh_board_backend.category.service;

import com.creativedesignproject.kumoh_board_backend.category.domain.entity.Category;
import com.creativedesignproject.kumoh_board_backend.category.dto.request.CategoryDto;
import com.creativedesignproject.kumoh_board_backend.category.dto.request.UpdateCategoryRequestDto;

import java.util.List;

public interface CategoryService {
    void registerCategory(Category categoryEntity);
    void updateCategory(Long category_id, UpdateCategoryRequestDto dto);
    void deleteCategoryName(Long category_id);
    List<CategoryDto> getCategoryList();
}
