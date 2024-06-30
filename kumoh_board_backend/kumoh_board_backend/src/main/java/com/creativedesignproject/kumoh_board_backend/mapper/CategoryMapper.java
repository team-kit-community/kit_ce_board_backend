package com.creativedesignproject.kumoh_board_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.creativedesignproject.kumoh_board_backend.Category.entity.CategoryEntity;

@Mapper
public interface CategoryMapper {
    public int CategorySave(CategoryEntity categoryEntity);
    public void updateCategory(String categoryName, int category_id);
    public void deleteCategory(int category_id);
    public boolean existedByCategoryName(String categoryName);
    public boolean existedByCategoryId(@Param("category_id") int category_id);
    public CategoryEntity findByCategoryId(int category_id);
    public List<CategoryEntity> findByCategoriesAll();
}
