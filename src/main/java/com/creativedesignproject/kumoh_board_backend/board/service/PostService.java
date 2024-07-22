package com.creativedesignproject.kumoh_board_backend.board.service;

import java.util.List;

import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.CategoryPostDto;
import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.CommentDto;
import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.FavoriteListDto;
import com.creativedesignproject.kumoh_board_backend.board.domain.repository.query.PostDto;
import com.creativedesignproject.kumoh_board_backend.board.dto.request.PatchBoardRequestDto;
import com.creativedesignproject.kumoh_board_backend.board.dto.request.PostBoardRequestDto;
import com.creativedesignproject.kumoh_board_backend.board.dto.request.PostCommentRequestDto;
import com.creativedesignproject.kumoh_board_backend.board.dto.request.PostSubCommentRequestDto;
import com.creativedesignproject.kumoh_board_backend.board.dto.response.PutFavoriteResponseDto;


public interface PostService {
    PostDto getPost(Long category_id, Long post_number);
    void registerPost(PostBoardRequestDto dto, Long category_id, String userId);
    void deletePost(Long category_id, Long post_number, String userId);
    void patchPost(Long category_id, Long post_number, String userId, PatchBoardRequestDto dto);
    List<PostDto> getLatestBoardList(Long category_id);
    List<PostDto> getTop3BoardList(Long category_id);
    List<PostDto> getSearchBoardList(String search_word, String relation_word);
    List<PostDto> getUserBoardList(String userId);
    List<CategoryPostDto> getCategoryOfBoardList();
    PutFavoriteResponseDto likeBoard(Long category_id, Long post_number, String userId);
    void addComment(Long category_id, Long post_number, String userId, PostCommentRequestDto dto);
    List<CommentDto> getCommentList(Long category_id, Long post_number);
    List<FavoriteListDto> getFavoriteList(Long category_id, Long post_number);
    void addSubComment(Long category_id, Long post_number, String userId, PostSubCommentRequestDto dto);
}

