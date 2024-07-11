package com.creativedesignproject.kumoh_board_backend.board.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.creativedesignproject.kumoh_board_backend.board.domain.Post;
import com.creativedesignproject.kumoh_board_backend.board.repository.query.PostDto;

import java.time.LocalDateTime;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final EntityManager em;

    public Post findById(Long post_number) {
        return em.find(Post.class, post_number);
    }

    public Post findByCategoryIdAndPostNumber(Long category_id, Long post_number) {
        return em.createQuery("select p from Post p where p.category.id = :category_id and p.id = :post_number", Post.class)
                .setParameter("category_id", category_id)
                .setParameter("post_number", post_number)
                .getSingleResult();
    }

    public boolean existsByCategoryIdAndPostNumber(Long category_id, Long post_number) {
        return em.createQuery("select p from Post p where p.category.id = :category_id and p.id = :post_number", Post.class)
                .setParameter("category_id", category_id)
                .setParameter("post_number", post_number)
                .getResultList().size() > 0;
    }

    public Post selectBoardWithImage(Long category_id, Long post_number) {
        return em.createQuery("select p from Post p" +
                            " join fetch p.images" + 
                            " where p.category.id = :category_id and p.id = :post_number", Post.class)
                .setParameter("category_id", category_id)
                .setParameter("post_number", post_number)
                .getSingleResult();
    }

    public boolean isOwner(Long category_id, Long post_number, String userId) {
        return em.createQuery("select p from Post p where p.category.id = :category_id and p.id = :post_number and p.user.user_id = :userId", Post.class)
                .setParameter("category_id", category_id)
                .setParameter("post_number", post_number)
                .setParameter("userId", userId)
                .getResultList().size() > 0;
    }

    public void save(Post post) {
        em.persist(post);
    }

    public void deleteByPostNumberAndCategoryId(Long post_number, Long category_id) {
        em.createQuery("delete from Post p where p.id = :post_number and p.category.id = :category_id")
                .setParameter("post_number", post_number)
                .setParameter("category_id", category_id)
                .executeUpdate();
    }

    public List<PostDto> selectLatestBoardList(Long category_id) {
        return em.createQuery("select new com.creativedesignproject.kumoh_board_backend.Board.repository.query.PostDto(p.title, p.contents, p.favorite_count, p.comment_count, p.view_count, p.user.nickname, p.category.name, p.updatedDate) from Post p where p.category.id = :category_id order by p.updatedDate desc", PostDto.class)
                .setParameter("category_id", category_id)
                .getResultList();
    }

    public List<PostDto> selectTop3BoardList(LocalDateTime sevenDaysAgoDate, Long category_id) {
        return em.createQuery("select new com.creativedesignproject.kumoh_board_backend.Board.repository.query.PostDto(p.title, p.contents, p.favorite_count, p.comment_count, p.view_count, p.user.nickname, p.category.name, p.updatedDate) from Post p where p.category.id = :category_id and p.updatedDate >= :sevenDaysAgoDate order by p.favorite_count desc", PostDto.class)
                .setParameter("category_id", category_id)
                .setParameter("sevenDaysAgoDate", sevenDaysAgoDate)
                .getResultList();
    }

    public List<PostDto> selectSearchBoardList(String search_word, String relation_word) {
        return em.createQuery(
                "select new com.creativedesignproject.kumoh_board_backend.Board.repository.query.PostDto(p.title, p.contents, p.favorite_count, p.comment_count, p.view_count, p.user.nickname, p.category.name, p.updatedDate) " +
                "from Post p " +
                "where p.title like :search_word or p.contents like :search_word or p.user.nickname like :search_word " +
                "order by p.updatedDate desc", PostDto.class)
                .setParameter("search_word", "%" + search_word + "%")
                .getResultList();
    }
    
    public List<PostDto> selectUserBoardList(String userId) {
        return em.createQuery(
                "select new com.creativedesignproject.kumoh_board_backend.Board.repository.query.PostDto(p.title, p.contents, p.favorite_count, p.comment_count, p.view_count, p.user.nickname, p.category.name, p.updatedDate) " +
                "from Post p " +
                "where p.user.user_id = :userId " +
                "order by p.updatedDate desc", PostDto.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public boolean existsByPostNumberAndCategoryId(Long post_number, Long category_id) {
        return em.createQuery("select p from Post p where p.id = :post_number and p.category.id = :category_id", Post.class)
                .setParameter("post_number", post_number)
                .setParameter("category_id", category_id)
                .getResultList().size() > 0;
    }
}
