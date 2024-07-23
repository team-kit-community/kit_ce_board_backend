package com.creativedesignproject.kumoh_board_backend.board.domain.repository.post;

import org.springframework.data.repository.query.Param;

import com.creativedesignproject.kumoh_board_backend.board.domain.entity.Post;
import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.PostDto;

import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;

public interface QuerydslPostRepository {
    Optional<Post> selectBoardWithImage(@Param("categoryId") Long categoryId, @Param("postId") Long postId);

    boolean isOwner(@Param("categoryId") Long categoryId, @Param("postId") Long postId, @Param("userId") String userId); // 커스텀 쿼리 추가
    
    List<PostDto> selectLatestBoardList(@Param("category_id") Long category_id, @Param("sevenDaysAgoDate") LocalDateTime sevenDaysAgoDate);

    List<PostDto> selectTop3BoardList(@Param("sevenDaysAgoDate") LocalDateTime sevenDaysAgoDate, @Param("category_id") Long category_id);

    List<PostDto> selectSearchBoardList(@Param("searchWord") String searchWord, @Param("relationWord") String relationWord);

    List<PostDto> selectUserBoardList(@Param("userId") String userId);
}
