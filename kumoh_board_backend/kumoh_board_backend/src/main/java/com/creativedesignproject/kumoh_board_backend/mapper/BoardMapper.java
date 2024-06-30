package com.creativedesignproject.kumoh_board_backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.creativedesignproject.kumoh_board_backend.Board.entity.BoardEntity;
import com.creativedesignproject.kumoh_board_backend.Board.entity.BoardListEntity;
import com.creativedesignproject.kumoh_board_backend.Board.entity.CategoryBoardListEntity;
import com.creativedesignproject.kumoh_board_backend.Board.entity.CommentEntity;
import com.creativedesignproject.kumoh_board_backend.Board.entity.ImageEntity;

import java.util.List;

@Mapper
public interface BoardMapper {
    //특정 게시물 조회
    public BoardEntity selectBoardWithImage(@Param("category_id") Integer category_id, @Param("post_number") Integer post_number);
    public boolean existedBoard(@Param("category_id") Integer category_id, @Param("post_number") Integer post_number);    
    public void increaseViewCount(@Param("post_number") Integer post_number);

    //게시물 등록
    public void registerBoard(BoardEntity boardEntity);
    public void registerBoardImage(List<ImageEntity> imageEntities);

    //게시물 삭제
    public boolean isOwner(@Param("category_id") Integer category_id, @Param("post_number") Integer post_number, String userId);
    public void deleteBoard(@Param("category_id") Integer category_id, @Param("post_number") Integer post_number);

    //게시물 수정
    public void patchBoard(BoardEntity boardEntity);
    public void deleteByPostNumberAndCategoryId(@Param("post_number") Integer post_number, @Param("category_id") Integer category_id);

    //최신 게시물 리스트 조회
    public List<BoardListEntity> selectLatestBoardList(@Param("category_id") Integer category_id);

    //Top3 게시물 리스트 조회
    public List<BoardListEntity> selectTop3BoardList(String sevenDaysAgo, @Param("category_id") Integer category_id);

    //검색 게시물 리스트 조회
    public List<BoardListEntity> selectSearchBoardList(@Param("search_word") String search_word, @Param("relation_word") String relation_word);

    //특정 유저 게시물 리스트 조회
    public List<BoardListEntity> selectUserBoardList(String userId);

    //메인 화면 게시물 리스트 조회
    public List<CategoryBoardListEntity> selectCategoryOfBoardList();

    //좋아요 리스트로 인한 post 업데이트
    public void updateFavoriteCount(@Param("post_number") Integer post_number, @Param("favorite_count") Integer favorite_count);

    public void updateCommentCount(@Param("post_number") Integer post_number, @Param("comment_count") Integer comment_count);
    //댓글 작성
    public void addComment(CommentEntity commentEntity);

    public void save(BoardEntity boardEntity);
    
    @Select("SELECT post_number, title, contents, write_datetime, favorite_count, comment_count, view_count, user_id, category_id FROM post WHERE category_id = #{categoryId} AND post_number = #{postNumber}")
    @Results({
        @Result(property = "post_number", column = "post_number"),
        @Result(property = "title", column = "title"),
        @Result(property = "contents", column = "contents"),
        @Result(property = "write_datetime", column = "write_datetime"),
        @Result(property = "favorite_count", column = "favorite_count"),
        @Result(property = "comment_count", column = "comment_count"),
        @Result(property = "view_count", column = "view_count"),
        @Result(property = "user_id", column = "user_id"),
        @Result(property = "category_id", column = "category_id")
    })
    public BoardEntity findByPostNumber(@Param("categoryId") int categoryId, @Param("postNumber") int postNumber);

    //댓글 리스트
    public List<CommentEntity> selectCommentsByPostNumber(@Param("post_number") Integer post_number);
    
    //대댓글 리스트
    public List<CommentEntity> selectSubcommentsByCommentId(@Param("comment_id") Integer comment_id);
}