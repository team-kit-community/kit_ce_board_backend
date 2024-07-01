package com.creativedesignproject.kumoh_board_backend.Board.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.creativedesignproject.kumoh_board_backend.Board.entity.Image;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ImageRepository {
    private final EntityManager em;

    public void saveAll(List<Image> images) {
        em.persist(images);
    }
}
