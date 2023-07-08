package com.example.rado.domain.board.domain.repository;

import com.example.rado.domain.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findBoardById(Long id);
}
