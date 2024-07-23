package com.creativedesignproject.kumoh_board_backend.category.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.creativedesignproject.kumoh_board_backend.category.domain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>, QuerydslCategoryRepository {

    boolean existsByName(String name);

    boolean existsById(Long id);

    Optional<Category> findById(Long category_id);

    List<Category> findAll();
}