package com.creativedesignproject.kumoh_board_backend.Board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creativedesignproject.kumoh_board_backend.Board.service.BoardService;

import jakarta.validation.Valid;

import com.creativedesignproject.kumoh_board_backend.Board.dto.request.PatchBoardRequestDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.request.PostBoardRequestDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.request.PostCommentRequestDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.request.PostSubCommentRequestDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetBoardResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.PostBoardResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.DeleteBoardResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.PatchBoardResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetLatestBoardListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetTop3BoardListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetSearchBoardListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetUserBoardListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetCategoryOfBoardListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.PutFavoriteResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.PostCommentResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetCommentListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetFavoriteListResponseDto;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    
    @GetMapping("/{category_id}/{post_number}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<? super GetBoardResponseDto> getBoard(
            @PathVariable Integer category_id, @PathVariable Integer post_number) {
        return boardService.getBoard(category_id, post_number);
    }

    @PostMapping("/{category_id}/register")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<? super PostBoardResponseDto> registerBoard(@RequestBody @Valid PostBoardRequestDto dto, @PathVariable Integer category_id, @AuthenticationPrincipal String userId) {
        return boardService.registerBoard(dto, category_id, userId);
    }

    @DeleteMapping("/{category_id}/{post_number}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(@PathVariable Integer category_id, @PathVariable Integer post_number, @AuthenticationPrincipal String userId) {
        return boardService.deleteBoard(category_id, post_number, userId);
    }

    @PatchMapping("/{category_id}/{post_number}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(@PathVariable Integer category_id, @PathVariable Integer post_number, @AuthenticationPrincipal String userId,@RequestBody @Valid PatchBoardRequestDto dto) {
        return boardService.patchBoard(category_id, post_number, userId, dto);
    }

    @GetMapping("/{category_id}/latest_list")
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList(@PathVariable Integer category_id) {
        return boardService.getLatestBoardList(category_id);
    }

    @GetMapping("/{category_id}/Top_3")
    public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList(@PathVariable Integer category_id) {
        return boardService.getTop3BoardList(category_id);
    }

    @GetMapping(value = {"/Search_list/{search_word}", "/Search_list/{search_word}/{relation_word}"})
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(@PathVariable String search_word, @PathVariable(required=false) String relation_word) {
        return boardService.getSearchBoardList(search_word, relation_word);
    }

    @GetMapping("/user-board-list/{userId}")
    public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(@PathVariable("userId") String userId) {
        return boardService.getUserBoardList(userId);
    }

    @GetMapping("/main-list")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<? super GetCategoryOfBoardListResponseDto> getCategoryOfBoardList() {
        return boardService.getCategoryOfBoardList();
    }

    @PutMapping("/{category_id}/{post_number}/favorite")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<? super PutFavoriteResponseDto> likeBoard(@PathVariable Integer category_id, @PathVariable Integer post_number, @AuthenticationPrincipal String userId) {
        return boardService.likeBoard(category_id, post_number, userId);
    }

    @GetMapping("/{category_id}/{post_number}/favorite-list")
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(
            @PathVariable Integer category_id,
            @PathVariable Integer post_number) {
        return boardService.getFavoriteList(category_id, post_number);
    }

    @PostMapping("/{category_id}/{post_number}/comment")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<? super PostCommentResponseDto> addComment(@PathVariable Integer category_id, @PathVariable Integer post_number, @AuthenticationPrincipal String userId, @RequestBody @Valid PostCommentRequestDto dto) {
        return boardService.addComment(category_id, post_number, userId, dto);
    }

    @PostMapping("/{category_id}/{post_number}/subComment")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<? super PostCommentResponseDto> addSubComment(@PathVariable Integer category_id,
            @PathVariable Integer post_number, @AuthenticationPrincipal String userId,
            @RequestBody @Valid PostSubCommentRequestDto dto) {
        return boardService.addSubComment(category_id, post_number, userId, dto);
    }

    @GetMapping("/{category_id}/{post_number}/comment-list")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(@PathVariable Integer category_id, @PathVariable Integer post_number) {
        return boardService.getCommentList(category_id, post_number);
    }
}

