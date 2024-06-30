package com.creativedesignproject.kumoh_board_backend.Category.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creativedesignproject.kumoh_board_backend.Category.entity.CategoryEntity;
import com.creativedesignproject.kumoh_board_backend.Category.service.CategoryService;

import jakarta.validation.Valid;

import com.creativedesignproject.kumoh_board_backend.Category.dto.response.RegisterCategoryResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.response.UpdateCategoryResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.request.UpdateCategoryRequestDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.response.DeleteCategoryResponseDto;
import com.creativedesignproject.kumoh_board_backend.Category.dto.response.GetCategoryListResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<? super RegisterCategoryResponseDto> registerCategory(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.registerCategory(categoryEntity);
    }

    @PatchMapping("/update/{category_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<? super UpdateCategoryResponseDto> updateCategory(@PathVariable int category_id, @RequestBody @Valid UpdateCategoryRequestDto dto) {
        return categoryService.updateCategory(category_id, dto);
    }

    @DeleteMapping("/delete/{category_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<? super DeleteCategoryResponseDto> deleteCategory(@PathVariable int category_id) {
        return categoryService.deleteCategoryName(category_id);
    }

    @GetMapping("")
    public ResponseEntity<? super GetCategoryListResponseDto> getCategory() {
        return categoryService.getCategoryList();
    }
}
