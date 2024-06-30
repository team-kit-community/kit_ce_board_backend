package com.creativedesignproject.kumoh_board_backend.Board.service.Impl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.entity.UserEntity;
import com.creativedesignproject.kumoh_board_backend.Board.dto.request.PatchBoardRequestDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.request.PostBoardRequestDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.request.PostCommentRequestDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.request.PostSubCommentRequestDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.DeleteBoardResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetBoardResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetCategoryOfBoardListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetCommentListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetFavoriteListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetLatestBoardListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetSearchBoardListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetTop3BoardListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.GetUserBoardListResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.PatchBoardResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.PostBoardResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.PostCommentResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.dto.response.PutFavoriteResponseDto;
import com.creativedesignproject.kumoh_board_backend.Board.entity.BoardEntity;
import com.creativedesignproject.kumoh_board_backend.Board.entity.BoardListEntity;
import com.creativedesignproject.kumoh_board_backend.Board.entity.CategoryBoardListEntity;
import com.creativedesignproject.kumoh_board_backend.Board.entity.CommentEntity;
import com.creativedesignproject.kumoh_board_backend.Board.entity.FavoriteEntity;
import com.creativedesignproject.kumoh_board_backend.Board.entity.ImageEntity;
import com.creativedesignproject.kumoh_board_backend.Board.entity.primary.FavoritePk;
import com.creativedesignproject.kumoh_board_backend.Board.resultSet.GetFavoriteListResultSet;
import com.creativedesignproject.kumoh_board_backend.Board.service.BoardService;
import com.creativedesignproject.kumoh_board_backend.Search.entity.SearchEntity;
import com.creativedesignproject.kumoh_board_backend.mapper.AuthMapper;
import com.creativedesignproject.kumoh_board_backend.mapper.BoardMapper;
import com.creativedesignproject.kumoh_board_backend.mapper.CategoryMapper;
import com.creativedesignproject.kumoh_board_backend.mapper.FavoriteMapper;
import com.creativedesignproject.kumoh_board_backend.mapper.SearchMapper;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{
    private final BoardMapper boardMapper;
    private final CategoryMapper categoryMapper;
    private final AuthMapper userMapper;
    private final SearchMapper searchMapper;
    private final FavoriteMapper favoriteMapper;

    @Transactional
    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer category_id, Integer post_number) {
        try {
            boolean isExisted = categoryMapper.existedByCategoryId(category_id);
            if(!isExisted) return GetBoardResponseDto.notExistedCategory();

            boolean isExistedBoard = boardMapper.existedBoard(category_id, post_number);
            if(!isExistedBoard) return GetBoardResponseDto.notExistedBoard();
            
            boardMapper.increaseViewCount(post_number);
            BoardEntity boardEntity = boardMapper.selectBoardWithImage(category_id, post_number);
            
            return GetBoardResponseDto.success(boardEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Transactional
    @Override
    public ResponseEntity<? super PostBoardResponseDto> registerBoard(PostBoardRequestDto dto, Integer category_id, String userId) {
        try {
            UserEntity user = userMapper.findUserId(userId)
                    .orElseThrow(()-> new UsernameNotFoundException("User not found"));

            BoardEntity boardEntity = BoardEntity.builder()
                    .title(dto.getTitle())
                    .contents(dto.getContent())
                    .write_datetime(new Date())
                    .user_id(user.getId())
                    .category_id(category_id)
                    .build();
            boardMapper.registerBoard(boardEntity);

            // 이미지 저장
            ImageSave(boardEntity.getPost_number(), dto.getBoardImageList());
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostBoardResponseDto.success();
    }

    @Transactional
    @Override
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer category_id, Integer post_number,String userId) {
        try {
            boolean isExisted = userMapper.existsByUserId(userId);
            if(!isExisted) return DeleteBoardResponseDto.notExistUser();

            boolean isExistedBoard = boardMapper.existedBoard(category_id, post_number);
            if(!isExistedBoard) return DeleteBoardResponseDto.notExistedBoard();

            boolean isOwner = boardMapper.isOwner(category_id, post_number, userId);
            if(!isOwner) return DeleteBoardResponseDto.noPermission();

            boardMapper.deleteBoard(category_id, post_number);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return DeleteBoardResponseDto.success();
    }

    @Transactional
    @Override
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(Integer category_id, Integer post_number, String userId, PatchBoardRequestDto dto) {
        try {
            boolean isExisted = userMapper.existsByUserId(userId);
            if (!isExisted) return PatchBoardResponseDto.notExistUser();

            boolean isExistedBoard = boardMapper.existedBoard(category_id, post_number);
            if (!isExistedBoard) return PatchBoardResponseDto.notExistBoard();

            boolean isOwner = boardMapper.isOwner(category_id, post_number, userId);
            if (!isOwner) return PatchBoardResponseDto.noPermission();

            BoardEntity boardEntity = BoardEntity.builder()
                    .title(dto.getTitle())
                    .contents(dto.getContent())
                    .post_number(post_number)
                    .category_id(category_id)
                    .build();
            boardMapper.patchBoard(boardEntity);
            
            boardMapper.deleteByPostNumberAndCategoryId(post_number, category_id);
            ImageSave(post_number, dto.getBoardImageList());
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchBoardResponseDto.success();
    }

    private void ImageSave(Integer post_number, List<String> boardImageList) {
        if (boardImageList != null && !boardImageList.isEmpty()) {
            List<ImageEntity> imageEntities = new ArrayList<>();
            for (String image : boardImageList) {
                ImageEntity imageEntity = ImageEntity.builder()
                        .post_number(post_number)
                        .image(image)
                        .build();
                imageEntities.add(imageEntity);
            }
            boardMapper.registerBoardImage(imageEntities);
        }
    }

    @Override
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList(Integer category_id) {
        try {
            List<BoardListEntity> boardListEntities = boardMapper.selectLatestBoardList(category_id);
            return GetLatestBoardListResponseDto.success(boardListEntities);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList(Integer category_id) {
        try {
            List<BoardListEntity> boardListEntities = new ArrayList<>();
            Date beforeWeek = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sevenDaysAgo = sdf.format(beforeWeek);
            boardListEntities = boardMapper.selectTop3BoardList(sevenDaysAgo, category_id);
            return GetTop3BoardListResponseDto.success(boardListEntities);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String search_word, String relation_word) {
        try {
            List<BoardListEntity> boardListEntities = new ArrayList<>();
            boardListEntities = boardMapper.selectSearchBoardList(search_word, relation_word);

            SearchEntity searchEntity = SearchEntity.builder()
                    .search_word(search_word)
                    .relation_word(relation_word)
                    .relation(false)
                    .build();
            searchMapper.searchLogSave(searchEntity);
            boolean relation = relation_word != null;

            if (relation) {
                searchEntity = SearchEntity.builder()
                        .search_word(relation_word)
                        .relation_word(search_word)
                        .relation(relation)
                        .build();
                searchMapper.searchLogSave(searchEntity);
            }
            return GetSearchBoardListResponseDto.success(boardListEntities);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String userId) {
        List<BoardListEntity> boardListEntities = new ArrayList<>();

        try {
            boolean existedUser = userMapper.existsByUserId(userId);
            if (!existedUser) return GetUserBoardListResponseDto.notExistUser();

            boardListEntities = boardMapper.selectUserBoardList(userId);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetUserBoardListResponseDto.success(boardListEntities);
    }

    @Override
    public ResponseEntity<? super GetCategoryOfBoardListResponseDto> getCategoryOfBoardList() {
        try {
            List<CategoryBoardListEntity> boardListEntities = boardMapper.selectCategoryOfBoardList();
            return GetCategoryOfBoardListResponseDto.success(boardListEntities);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Transactional
    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> likeBoard(Integer category_id, Integer post_number, String userId) {
        try {
            boolean isExisted = userMapper.existsByUserId(userId);
            if(!isExisted) return PutFavoriteResponseDto.notExistUser();

            log.info("Finding board with category_id: {}, post_number: {}", category_id, post_number);
            BoardEntity boardEntity = boardMapper.findByPostNumber(category_id, post_number);
            if(boardEntity == null) return PutFavoriteResponseDto.notExistBoard();

            log.info("Found BoardEntity: {}", boardEntity);

            UserEntity user = userMapper.findByUserId(userId);

            boolean favoriteExists = favoriteMapper.findByBoardNumberAndUserId(user.getId(), post_number);
            if (!favoriteExists) {
                FavoriteEntity favoriteEntity = new FavoriteEntity(user.getId(), post_number);
                favoriteMapper.save(favoriteEntity);
                boardEntity.setFavorite_count(boardEntity.getFavorite_count() + 1);
            } else {
                favoriteMapper.delete(user.getId(), post_number);
                if (boardEntity.getFavorite_count() > 0) {
                    boardEntity.setFavorite_count(boardEntity.getFavorite_count() - 1);
                }
            }

            log.info("Updating favorite count for post_number: {}, new favorite_count: {}", boardEntity.getPost_number(), boardEntity.getFavorite_count());
            boardMapper.updateFavoriteCount(boardEntity.getPost_number(), boardEntity.getFavorite_count());
            return PutFavoriteResponseDto.success(boardEntity.getFavorite_count());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Transactional
    @Override
    public ResponseEntity<? super PostCommentResponseDto> addComment(Integer category_id, Integer post_number, String userId,
            PostCommentRequestDto dto) {
        try {
            boolean isExisted = userMapper.existsByUserId(userId);
            if (!isExisted) return PostCommentResponseDto.notExistUser();

            CommentEntity commentEntity = CommentEntity.builder()
                    .post_number(post_number)
                    .contents(dto.getContent())
                    .write_datetime(new Date())
                    .subcomment_id(dto.getSubcomment_id() != null ? dto.getSubcomment_id() : 0) // 대댓글인 경우 부모 댓글 ID 설정
                    .user_id(userMapper.findUserId(userId)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found")).getId())
                    .build();
            
            boardMapper.addComment(commentEntity);
            BoardEntity boardEntity = boardMapper.findByPostNumber(category_id, post_number);
            boardEntity.increaseCommentCount();
            boardMapper.updateCommentCount(boardEntity.getPost_number(), boardEntity.getComment_count());
            return PostCommentResponseDto.success();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer category_id, Integer post_number) {
        try {
            List<CommentEntity> comments = boardMapper.selectCommentsByPostNumber(post_number);
            List<CommentEntity> allComments = new ArrayList<>();
            
            for (CommentEntity comment : comments) {
                if(comment.getSubcomment_id() == 0) { // 대댓글이 없는 경우
                    allComments.add(comment);
                } else { // 대댓글이 있는 경우
                    CommentEntity parentComment = findParentComment(allComments, comment.getSubcomment_id());
                    if (parentComment != null) {
                        parentComment.getSubcomments().add(comment);
                    }
                }
            }
            return GetCommentListResponseDto.success(allComments);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    private CommentEntity findParentComment(List<CommentEntity> comments, Integer parentId) {
        for (CommentEntity comment : comments) {
            if (comment.getComment_id() == parentId) {
                return comment;
            }
        }
        return null;
    }

    @Override
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer category_id,
            Integer post_number) {
        List<GetFavoriteListResultSet> resultSets = new ArrayList<>();
        
        try {
            boolean existedBoard = boardMapper.existedBoard(category_id, post_number);
            if(!existedBoard) return GetFavoriteListResponseDto.notExistBoard();

            resultSets = favoriteMapper.getFavoriteList(post_number);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetFavoriteListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super PostCommentResponseDto> addSubComment(Integer category_id, Integer post_number,
            String userId, PostSubCommentRequestDto dto) {
        try {
            boolean isExisted = userMapper.existsByUserId(userId);
            if (!isExisted)
                return PostCommentResponseDto.notExistUser();
            
            CommentEntity subCommentEntity = CommentEntity.builder()
                    .post_number(post_number)
                    .contents(dto.getContent())
                    .write_datetime(new Date())
                    .subcomment_id(dto.getParent_comment_id()) // 부모 댓글 ID 설정
                    .user_id(userMapper.findUserId(userId)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found")).getId())
                    .build();
            boardMapper.addComment(subCommentEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostCommentResponseDto.success();
    }
}
