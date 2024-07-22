package com.creativedesignproject.kumoh_board_backend.board.domain.repository.post;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.creativedesignproject.kumoh_board_backend.board.domain.entity.Post;
import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.PostDto;

import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;

public interface QuerydslPostRepository {
    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.images WHERE p.category.id = :categoryId AND p.id = :postId")
    Optional<Post> selectBoardWithImage(@Param("categoryId") Long categoryId, @Param("postId") Long postId);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Post p WHERE p.category.id = :categoryId AND p.id = :postId AND p.user.id = :userId")
    boolean isOwner(@Param("categoryId") Long categoryId, @Param("postId") Long postId, @Param("userId") String userId); // 커스텀 쿼리 추가
    
    @Query("select new com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.PostDto(p.title, p.contents, p.favorite_count, p.comment_count, p.view_count, p.user.nickname, p.category.name, p.updatedDate) from Post p where p.category.id = :category_id and p.updatedDate >= :sevenDaysAgoDate order by p.updatedDate desc")
    List<PostDto> selectLatestBoardList(@Param("category_id") Long category_id, @Param("sevenDaysAgoDate") LocalDateTime sevenDaysAgoDate);

    @Query("select new com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.PostDto(p.title, p.contents, p.favorite_count, p.comment_count, p.view_count, p.user.nickname, p.category.name, p.updatedDate) from Post p where p.category.id = :category_id and p.updatedDate >= :sevenDaysAgoDate order by p.favorite_count desc")
    List<PostDto> selectTop3BoardList(@Param("sevenDaysAgoDate") LocalDateTime sevenDaysAgoDate, @Param("category_id") Long category_id);

    @Query("SELECT new com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.PostDto(p.title, p.contents, p.favorite_count, p.comment_count, p.view_count, p.user.nickname, p.category.name, p.updatedDate) FROM Post p WHERE (p.title LIKE :searchWord OR p.contents LIKE :searchWord OR p.user.nickname LIKE :searchWord) AND (p.title LIKE :relationWord OR p.contents LIKE :relationWord OR p.user.nickname LIKE :relationWord) ORDER BY p.updatedDate DESC")
    List<PostDto> selectSearchBoardList(@Param("searchWord") String searchWord, @Param("relationWord") String relationWord);

    @Query("select new com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.PostDto(p.title, p.contents, p.favorite_count, p.comment_count, p.view_count, p.user.nickname, p.category.name, p.updatedDate) from Post p where p.user.userId = :userId order by p.updatedDate desc")
    List<PostDto> selectUserBoardList(@Param("userId") String userId);
}
