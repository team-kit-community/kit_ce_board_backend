package com.creativedesignproject.kumoh_board_backend.category.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.CategoryPostDto;
import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.PostDto;
import com.creativedesignproject.kumoh_board_backend.category.domain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);

    boolean existsByCategoryId(Long category_id);

    @Query("select new com.creativedesignproject.kumoh_board_backend.board.repository.query.CategoryPostDto(c.name, p.title, p.contents, p.favoriteCount, p.commentCount, p.viewCount, p.user.userName, p.updatedDate) from Category c join Post p on c.id = p.category.id order by p.updatedDate desc")
    List<CategoryPostDto> findAllCategoryPostDtos();

    @Query("select new com.creativedesignproject.kumoh_board_backend.board.repository.query.PostDto(p.title, p.contents, p.favoriteCount, p.commentCount, p.viewCount, p.user.userName, p.category.name, p.updatedDate) from Post p where p.category.id = :categoryId order by p.updatedDate desc")
    List<PostDto> selectRecentPostsByCategory(Long categoryId);

    Optional<Category> findById(Long category_id);

    List<Category> findAll();

    List<Long> findAllIds();
}