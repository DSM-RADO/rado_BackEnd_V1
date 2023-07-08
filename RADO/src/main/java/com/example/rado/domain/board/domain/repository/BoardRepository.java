package com.example.backend.domain.board.domain.repository;

import com.example.backend.domain.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findBoardById(Long id);

    @Query(value = "select b from Board b where b.title LIKE %:kda%")
    List<Board> findAllBoardByTitleSearch(String kda);
}
