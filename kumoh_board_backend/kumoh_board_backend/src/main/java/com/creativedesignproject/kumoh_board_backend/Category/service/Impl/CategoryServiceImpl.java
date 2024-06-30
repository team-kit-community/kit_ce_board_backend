package com.creativedesignproject.kumoh_board_backend.Category.service.Impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.request.UpdateCategoryRequestDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.response.DeleteCategoryResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.response.GetCategoryListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.response.RegisterCategoryResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.response.UpdateCategoryResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.entity.CategoryEntity;
import com.creativedesignproject.kumoh_board_backend.Category.service.CategoryService;
import com.creativedesignproject.kumoh_board_backend.mapper.CategoryMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryMapper categoryMapper;

    @Override
    public ResponseEntity<? super RegisterCategoryResponseDto> registerCategory(CategoryEntity categoryEntity) {
        try {
            boolean isExistedCategory = categoryMapper.existedByCategoryName(categoryEntity.getName());
            if(isExistedCategory) return RegisterCategoryResponseDto.DuplicatedCategoryName();

            categoryMapper.CategorySave(categoryEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return RegisterCategoryResponseDto.success();
    }

    @Override
    public ResponseEntity<? super UpdateCategoryResponseDto> updateCategory(int category_id, UpdateCategoryRequestDto dto) {
        try {
            CategoryEntity categoryEntity = categoryMapper.findByCategoryId(category_id);
            if(categoryEntity == null) return UpdateCategoryResponseDto.notExistedCategory();

            boolean isExistedCategory = categoryMapper.existedByCategoryName(dto.getName());
            if(isExistedCategory) return UpdateCategoryResponseDto.DuplicatedCategoryName();

            categoryMapper.updateCategory(dto.getName(), category_id);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return UpdateCategoryResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteCategoryResponseDto> deleteCategoryName(int category_id) {
        try {
            CategoryEntity categoryEntity = categoryMapper.findByCategoryId(category_id);
            if(categoryEntity == null) return DeleteCategoryResponseDto.notExistedCategory();

            categoryMapper.deleteCategory(category_id);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return DeleteCategoryResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetCategoryListResponseDto> getCategoryList() {
        List<CategoryEntity> categoryEntityList = null;
        
        try {
            categoryEntityList = categoryMapper.findByCategoriesAll();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCategoryListResponseDto.success(categoryEntityList);
    }
}
