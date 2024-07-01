package com.creativedesignproject.kumoh_board_backend.Category.service.Impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.request.UpdateCategoryRequestDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.response.DeleteCategoryResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.response.GetCategoryListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.response.RegisterCategoryResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.response.UpdateCategoryResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.entity.Category;
import com.creativedesignproject.kumoh_board_backend.Category.repository.CategoryRepository;
import com.creativedesignproject.kumoh_board_backend.Category.service.CategoryService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Transactional // 기본 값은 write
    @Override // 값 바꾸기
    public ResponseEntity<? super RegisterCategoryResponseDto> registerCategory(Category categoryEntity) {
        try {
            boolean isExistedCategory = categoryRepository.existsByName(categoryEntity.getName());
            if(isExistedCategory) return RegisterCategoryResponseDto.DuplicatedCategoryName();

            categoryRepository.save(categoryEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return RegisterCategoryResponseDto.success();
    }

    @Transactional // 기본 값은 write
    @Override // 값 바꾸기
    public ResponseEntity<? super UpdateCategoryResponseDto> updateCategory(Long category_id, UpdateCategoryRequestDto dto) {
        try {
            Category category = categoryRepository.findById(category_id);
            if(category == null) return UpdateCategoryResponseDto.notExistedCategory();

            boolean isExistedCategory = categoryRepository.existsByName(dto.getName());
            if(isExistedCategory) return UpdateCategoryResponseDto.DuplicatedCategoryName();

            category.setName(dto.getName()); // 트랜젝션 내에 있는 영속성 context에 변경이 일어나면 자동으로 플러시됨.
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return UpdateCategoryResponseDto.success();
    }

    @Transactional // 기본 값은 write
    @Override // 값 바꾸기
    public ResponseEntity<? super DeleteCategoryResponseDto> deleteCategoryName(Long category_id) {
        try {
            Category category = categoryRepository.findById(category_id);
            if(category == null) return DeleteCategoryResponseDto.notExistedCategory();

            categoryRepository.delete(category);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return DeleteCategoryResponseDto.success();
    }

    @Override // 값 읽기
    public ResponseEntity<? super GetCategoryListResponseDto> getCategoryList() {
        List<Category> categoryList = null;
        
        try {
            categoryList = categoryRepository.findAll();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCategoryListResponseDto.success(categoryList);
    }
}
