package com.creativedesignproject.kumoh_board_backend.category.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creativedesignproject.kumoh_board_backend.category.domain.Category;
import com.creativedesignproject.kumoh_board_backend.category.dto.request.CategoryDto;
import com.creativedesignproject.kumoh_board_backend.category.dto.request.UpdateCategoryRequestDto;
import com.creativedesignproject.kumoh_board_backend.category.repository.CategoryRepository;
import com.creativedesignproject.kumoh_board_backend.category.service.CategoryService;
import com.creativedesignproject.kumoh_board_backend.common.exception.BadRequestException;
import com.creativedesignproject.kumoh_board_backend.common.exception.ErrorCode;

import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Transactional // 기본 값은 write
    @Override // 값 바꾸기
    public void registerCategory(Category categoryEntity) {
        boolean isExistedCategory = categoryRepository.existsByName(categoryEntity.getName());
        if(isExistedCategory) throw new BadRequestException(ErrorCode.DUPLICATED_CATEGORY_NAME);

        categoryRepository.save(categoryEntity);
    }

    @Transactional // 기본 값은 write
    @Override // 값 바꾸기
    public void updateCategory(Long category_id, UpdateCategoryRequestDto dto) {
        Category category = categoryRepository.findById(category_id);
        if(category == null) throw new BadRequestException(ErrorCode.NOT_EXISTED_CATEGORY);

        boolean isExistedCategory = categoryRepository.existsByName(dto.getName());
        if(isExistedCategory) throw new BadRequestException(ErrorCode.DUPLICATED_CATEGORY_NAME);

        category.setName(dto.getName()); // 트랜젝션 내에 있는 영속성 context에 변경이 일어나면 자동으로 플러시됨.
    }

    @Transactional // 기본 값은 write
    @Override // 값 바꾸기
    public void deleteCategoryName(Long category_id) {
        Category category = categoryRepository.findById(category_id);
        if(category == null) throw new BadRequestException(ErrorCode.NOT_EXISTED_CATEGORY);

        categoryRepository.delete(category);
    }

    @Override // 값 읽기
    public List<CategoryDto> getCategoryList() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = convertToDTO(categoryList);
        return categoryDtoList;
    }

    private List<CategoryDto> convertToDTO(List<Category> categoryList) {
        return categoryList.stream()
                .map(category -> CategoryDto.builder()
                        .name(category.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
