package com.example.backend.domain.board.domain.repository;

import com.example.backend.domain.board.domain.Board;
import com.example.backend.domain.board.domain.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {
    void deleteAllByBoard(Board board);
    List<BoardImage> findByBoard(Board board);
}
