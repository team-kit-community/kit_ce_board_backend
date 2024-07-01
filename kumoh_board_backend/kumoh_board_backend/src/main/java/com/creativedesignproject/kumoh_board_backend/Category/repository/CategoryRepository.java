package com.creativedesignproject.kumoh_board_backend.Category.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.creativedesignproject.kumoh_board_backend.Board.repository.query.PostDto;
import com.creativedesignproject.kumoh_board_backend.Category.entity.Category;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private final EntityManager em;

    public boolean existsByName(String name) {
        return em.createQuery("select c from Category c where c.name = :name", Category.class)
                .setParameter("name", name)
                .getResultList().size() > 0;
    }

    public boolean existsById(Long category_id) {
        return em.createQuery("select c from Category c where c.id = :category_id", Category.class)
                .setParameter("category_id", category_id)
                .getResultList().size() > 0;
    }

    public List<PostDto> selectRecentPostsByCategory(Long categoryId) {
        return em.createQuery(
                "select new com.creativedesignproject.kumoh_board_backend.Board.repository.query.PostDto(p.title, p.contents, p.favoriteCount, p.commentCount, p.viewCount, p.user.userName, p.category.name, p.updatedDate) " +
                "from Post p " +
                "where p.category.id = :categoryId " +
                "order by p.updatedDate desc", PostDto.class)
                .setParameter("categoryId", categoryId)
                .setMaxResults(4)
                .getResultList();
    }

    public void save(Category category) {
        em.persist(category);
    }

    public Category findById(Long category_id) {
        return em.find(Category.class, category_id);
    }

    public void delete(Category category) {
        em.remove(category);
    }

    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Category.class)
                .getResultList();
    }

    public List<Long> findAllIds() {
        return em.createQuery("select c.id from Category c", Long.class)
                .getResultList();
    }
}