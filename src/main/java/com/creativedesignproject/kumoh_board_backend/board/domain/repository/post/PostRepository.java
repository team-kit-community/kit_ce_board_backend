package com.creativedesignproject.kumoh_board_backend.board.domain.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creativedesignproject.kumoh_board_backend.board.domain.entity.Post;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, QuerydslPostRepository {
    boolean existsByCategoryIdAndId(Long category_id, Long id);
    void deleteByCategoryIdAndId(Long category_id, Long id);
    Optional<Post> findById(Long post_number);
    Optional<Post> findByCategoryIdAndId(Long category_id, Long id);
}
