package com.creativedesignproject.kumoh_board_backend.board.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.creativedesignproject.kumoh_board_backend.board.domain.entity.Post;
import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.PostDto;

import java.time.LocalDateTime;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long post_number);

    Optional<Post> findByCategoryIdAndPostNumber(Long category_id, Long post_number);

    boolean existsByCategoryIdAndPostNumber(Long category_id, Long post_number);

    Optional<Post> selectBoardWithImage(Long category_id, Long post_number);

    boolean isOwner(Long category_id, Long post_number, String userId);

    void deleteByPostNumberAndCategoryId(Long post_number, Long category_id);

    @Query("select new com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.PostDto(p.title, p.contents, p.favorite_count, p.comment_count, p.view_count, p.user.nickname, p.category.name, p.updatedDate) from Post p where p.category.id = :category_id and p.updatedDate >= :sevenDaysAgoDate order by p.updatedDate desc")
    List<PostDto> selectLatestBoardList(@Param("category_id") Long category_id, @Param("sevenDaysAgoDate") LocalDateTime sevenDaysAgoDate);

    @Query("select new com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.PostDto(p.title, p.contents, p.favorite_count, p.comment_count, p.view_count, p.user.nickname, p.category.name, p.updatedDate) from Post p where p.category.id = :category_id and p.updatedDate >= :sevenDaysAgoDate order by p.favorite_count desc")
    List<PostDto> selectTop3BoardList(@Param("sevenDaysAgoDate") LocalDateTime sevenDaysAgoDate, @Param("category_id") Long category_id);

    @Query("select new com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.PostDto(p.title, p.contents, p.favorite_count, p.comment_count, p.view_count, p.user.nickname, p.category.name, p.updatedDate) from Post p where p.title like :search_word or p.contents like :search_word or p.user.nickname like :search_word order by p.updatedDate desc")
    List<PostDto> selectSearchBoardList(@Param("search_word") String search_word, @Param("relation_word") String relation_word);

    @Query("select new com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.PostDto(p.title, p.contents, p.favorite_count, p.comment_count, p.view_count, p.user.nickname, p.category.name, p.updatedDate) from Post p where p.user.user_id = :userId order by p.updatedDate desc")
    List<PostDto> selectUserBoardList(@Param("userId") String userId);

    boolean existsByPostNumberAndCategoryId(Long post_number, Long category_id);
}
