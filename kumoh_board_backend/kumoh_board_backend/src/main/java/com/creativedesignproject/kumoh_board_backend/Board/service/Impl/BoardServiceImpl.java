package com.creativedesignproject.kumoh_board_backend.Board.service.Impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creativedesignproject.kumoh_board_backend.Auth.dto.response.ResponseDto;
import com.creativedesignproject.kumoh_board_backend.Auth.entity.User;
import com.creativedesignproject.kumoh_board_backend.Auth.repository.UserRepository;
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
import com.creativedesignproject.kumoh_board_backend.Board.entity.Comment;
import com.creativedesignproject.kumoh_board_backend.Board.entity.Favorite;
import com.creativedesignproject.kumoh_board_backend.Board.entity.Image;
import com.creativedesignproject.kumoh_board_backend.Board.entity.Post;
import com.creativedesignproject.kumoh_board_backend.Board.entity.SubComment;
import com.creativedesignproject.kumoh_board_backend.Board.repository.CommentRepository;
import com.creativedesignproject.kumoh_board_backend.Board.repository.FavoriteRepository;
import com.creativedesignproject.kumoh_board_backend.Board.repository.ImageRepository;
import com.creativedesignproject.kumoh_board_backend.Board.repository.PostRepository;
import com.creativedesignproject.kumoh_board_backend.Board.repository.SubCommentRepository;
import com.creativedesignproject.kumoh_board_backend.Board.repository.query.CategoryPostDto;
import com.creativedesignproject.kumoh_board_backend.Board.repository.query.FavoriteListDto;
import com.creativedesignproject.kumoh_board_backend.Board.repository.query.PostDto;
import com.creativedesignproject.kumoh_board_backend.Board.service.BoardService;
import com.creativedesignproject.kumoh_board_backend.Category.entity.Category;
import com.creativedesignproject.kumoh_board_backend.Category.repository.CategoryRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final ImageRepository imageRepository;
    private final CommentRepository commentRepository;
    private final SubCommentRepository subCommentRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Long category_id, Long post_number) {
        try {
            boolean isExisted = categoryRepository.existsById(category_id);
            if(!isExisted) return GetBoardResponseDto.notExistedCategory();

            boolean isExistedBoard = postRepository.existsByCategoryIdAndPostNumber(category_id, post_number);
            if(!isExistedBoard) return GetBoardResponseDto.notExistedBoard();
            
            Post post = postRepository.selectBoardWithImage(category_id, post_number);
            post.increaseViewCount();
            
            return GetBoardResponseDto.success(post);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Transactional
    @Override
    public ResponseEntity<? super PostBoardResponseDto> registerBoard(PostBoardRequestDto dto, Long category_id, String userId) {
        try {
            User user = userRepository.findByUserId(userId);
            if(user == null) return PostBoardResponseDto.notExistUser();

            Category category = categoryRepository.findById(category_id);
            if(category == null) return PostBoardResponseDto.notExistCategory();

            Post post = Post.builder()
                    .title(dto.getTitle())
                    .contents(dto.getContent())
                    .user(user)
                    .category(category)
                    .build();
            postRepository.save(post);

            // 이미지 저장
            ImageSave(post.getId(), dto.getBoardImageList());
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostBoardResponseDto.success();
    }

    @Transactional
    @Override
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Long category_id, Long post_number,String userId) {
        try {
            boolean isExisted = userRepository.existsByUserId(userId);
            if(!isExisted) return DeleteBoardResponseDto.notExistUser();

            boolean isExistedPost = postRepository.existsByCategoryIdAndPostNumber(category_id, post_number);
            if(!isExistedPost) return DeleteBoardResponseDto.notExistedBoard();

            boolean isOwner = postRepository.isOwner(category_id, post_number, userId);
            if(!isOwner) return DeleteBoardResponseDto.noPermission();

            postRepository.deleteByPostNumberAndCategoryId(post_number, category_id);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return DeleteBoardResponseDto.success();
    }

    @Transactional
    @Override
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(Long category_id, Long post_number, String userId, PatchBoardRequestDto dto) {
        try {
            User user = userRepository.findByUserId(userId);
            if(user == null) return PatchBoardResponseDto.notExistUser();

            Category category = categoryRepository.findById(category_id);
            if(category == null) return PatchBoardResponseDto.notExistedCategory();

            boolean isOwner = postRepository.isOwner(category_id, post_number, userId);
            if (!isOwner) return PatchBoardResponseDto.noPermission();

            Post post = Post.builder()
                    .title(dto.getTitle())
                    .contents(dto.getContent())
                    .user(user)
                    .category(category)
                    .build();
            postRepository.save(post);
            
            postRepository.deleteByPostNumberAndCategoryId(post_number, category_id);
            ImageSave(post.getId(), dto.getBoardImageList());
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PatchBoardResponseDto.success();
    }

    private void ImageSave(Long post_number, List<String> boardImageList) {
        if (boardImageList != null && !boardImageList.isEmpty()) {
            Post post = postRepository.findById(post_number);

            List<Image> imageEntities = new ArrayList<>();
            for (String url : boardImageList) {
                Image image = Image.builder()
                        .post(post)
                        .url(url)
                        .build();
                imageEntities.add(image);
            }
            imageRepository.saveAll(imageEntities);
        }
    }

    @Override
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList(Long category_id) {
        try {
            List<PostDto> postList = postRepository.selectLatestBoardList(category_id);
            return GetLatestBoardListResponseDto.success(postList);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList(Long category_id) {
        try {
            List<PostDto> postList = postRepository.selectTop3BoardList(category_id);
            Date beforeWeek = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sevenDaysAgo = sdf.format(beforeWeek);
            postList = postRepository.selectTop3BoardList(sevenDaysAgo, category_id);
            return GetTop3BoardListResponseDto.success(postList);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String search_word, String relation_word) {
        try {
            List<PostDto> postList = postRepository.selectSearchBoardList(search_word, relation_word);
            return GetSearchBoardListResponseDto.success(postList);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String userId) {
        List<PostDto> postList = new ArrayList<>();

        try {
            boolean existedUser = userRepository.existsByUserId(userId);
            if (!existedUser) return GetUserBoardListResponseDto.notExistUser();

            postList = postRepository.selectUserBoardList(userId);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetUserBoardListResponseDto.success(postList);
    }

    @Override
    public ResponseEntity<? super GetCategoryOfBoardListResponseDto> getCategoryOfBoardList() {
        try {
            List<Long> categoryIds = categoryRepository.findAllIds();
            List<CategoryPostDto> postList = categoryIds.stream()
                .map(categoryId -> {
                    List<PostDto> posts = categoryRepository.selectRecentPostsByCategory(categoryId);
                    String categoryName = posts.isEmpty() ? "Unknown" : posts.get(0).getCategoryName();
                    return new CategoryPostDto(categoryName, posts);
                })
                .collect(Collectors.toList());
            return GetCategoryOfBoardListResponseDto.success(postList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Transactional
    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> likeBoard(Long category_id, Long post_number, String userId) {
        try {
            boolean isExisted = userRepository.existsByUserId(userId);
            if(!isExisted) return PutFavoriteResponseDto.notExistUser();

            Post post = postRepository.findById(post_number);
            if(post == null) return PutFavoriteResponseDto.notExistBoard();

            User user = userRepository.findByUserId(userId);

            boolean favoriteExists = favoriteRepository.findByBoardNumberAndUserId(user.getId(), post_number);
            if (!favoriteExists) {
                Favorite favoriteEntity = Favorite.builder()
                    .user(user)
                    .post(post)
                    .build();
                favoriteRepository.save(favoriteEntity);
                post.setFavorite_count(post.getFavorite_count() + 1);
            } else {
                favoriteRepository.delete(user.getId(), post_number);
                if (post.getFavorite_count() > 0) {
                    post.setFavorite_count(post.getFavorite_count() - 1);
                }
            }

            return PutFavoriteResponseDto.success(post.getFavorite_count());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Transactional
    @Override
    public ResponseEntity<? super PostCommentResponseDto> addComment(Long category_id, Long post_number, String userId,
            PostCommentRequestDto dto) {
        try {
            User user = userRepository.findByUserId(userId);
            if(user == null) return PostCommentResponseDto.notExistUser();

            Post post = postRepository.findById(post_number);
            if(post == null) return PostCommentResponseDto.notExistBoard();

            Comment comment = Comment.builder()
                    .contents(dto.getContent())
                    .user(user)
                    .post(post)
                    .build();
            
            commentRepository.save(comment);
            post.increaseCommentCount();
            return PostCommentResponseDto.success();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Long category_id, Long post_number) {
        try {
            List<Comment> comments = commentRepository.selectCommentsByPostNumber(post_number);
            List<Comment> allComments = new ArrayList<>();
            
            for (Comment comment : comments) {
                allComments.add(comment);
                List<SubComment> subComments = subCommentRepository.findByParentCommentId(comment.getId());
                comment.getSubcomments().addAll(subComments);
            }
            return GetCommentListResponseDto.success(allComments);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Long category_id,
            Long post_number) {
        List<FavoriteListDto> resultSets = new ArrayList<>();
        
        try {
            boolean existedBoard = postRepository.existsByPostNumberAndCategoryId(post_number, category_id);
            if(!existedBoard) return GetFavoriteListResponseDto.notExistBoard();

            resultSets = favoriteRepository.findByPostId(post_number);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetFavoriteListResponseDto.success(resultSets);
    }

    @Transactional
    @Override
    public ResponseEntity<? super PostCommentResponseDto> addSubComment(Long category_id, Long post_number,
            String userId, PostSubCommentRequestDto dto) {
        try {
            boolean isExisted = userRepository.existsByUserId(userId);
            if (!isExisted)
                return PostCommentResponseDto.notExistUser();
            
            User user = userRepository.findByUserId(userId);
            if(user == null) return PostCommentResponseDto.notExistUser();

            Post post = postRepository.findById(post_number);
            if(post == null) return PostCommentResponseDto.notExistBoard();

            Comment parentComment = commentRepository.findById(dto.getParent_comment_id());
            if(parentComment == null) return PostCommentResponseDto.notExistComment();

            SubComment subCommentEntity = SubComment.builder()
                .content(dto.getContent())
                .parentComment(parentComment)
                .build();

            subCommentRepository.save(subCommentEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostCommentResponseDto.success();
    }
}
