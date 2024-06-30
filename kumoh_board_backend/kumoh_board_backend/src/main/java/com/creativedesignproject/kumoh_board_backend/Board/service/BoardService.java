package com.creativedesignproject.kumoh_board_backend.Board.service;

import org.springframework.http.ResponseEntity;

import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetBoardResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.request.PatchBoardRequestDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.request.PostBoardRequestDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.request.PostCommentRequestDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.request.PostSubCommentRequestDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.PostBoardResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.DeleteBoardResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.PatchBoardResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetLatestBoardListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetTop3BoardListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetSearchBoardListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetUserBoardListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetCategoryOfBoardListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetCommentListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.PutFavoriteResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.PostCommentResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetFavoriteListResponseDto;


public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer category_id, Integer post_number);
    ResponseEntity<? super PostBoardResponseDto> registerBoard(PostBoardRequestDto dto, Integer category_id, String userId);
    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer category_id, Integer post_number, String userId);
    ResponseEntity<? super PatchBoardResponseDto> patchBoard(Integer category_id, Integer post_number, String userId, PatchBoardRequestDto dto);
    ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList(Integer category_id);
    ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList(Integer category_id);
    ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String search_word, String relation_word);
    ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String userId);
    ResponseEntity<? super GetCategoryOfBoardListResponseDto> getCategoryOfBoardList();
    ResponseEntity<? super PutFavoriteResponseDto> likeBoard(Integer category_id, Integer post_number, String userId);
    ResponseEntity<? super PostCommentResponseDto> addComment(Integer category_id, Integer post_number, String userId, PostCommentRequestDto dto);
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer category_id, Integer post_number);
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer category_id, Integer post_number);
    ResponseEntity<? super PostCommentResponseDto> addSubComment(Integer category_id, Integer post_number, String userId, PostSubCommentRequestDto dto);
}

