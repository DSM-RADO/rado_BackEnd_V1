package com.example.backend.domain.board.facade;

import com.example.backend.domain.board.domain.Board;
import com.example.backend.domain.board.domain.repository.BoardRepository;
import com.example.backend.global.error.ErrorCode;
import com.example.backend.global.error.exeception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardFacade {
    private final BoardRepository boardRepository;

    public Board getBoardById(Long id){
        return boardRepository.findBoardById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
    }

    public List<Board> getBoardAllById(Sort sort){
        return boardRepository.findAll(sort);
    }

    public List<Board> findAllBoardByTitleSearch(String kda) {
        return boardRepository.findAllBoardByTitleSearch(kda);
    }
}
