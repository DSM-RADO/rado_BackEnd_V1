package com.example.backend.domain.board.service;

import com.example.backend.domain.board.domain.Board;
import com.example.backend.domain.board.domain.BoardImage;
import com.example.backend.domain.board.domain.repository.BoardImageRepository;
import com.example.backend.domain.board.facade.BoardFacade;
import com.example.backend.domain.board.presentation.dto.response.QueryBoardDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryBoardDetailService {
    private final BoardFacade boardFacade;
    private final BoardImageRepository boardImageRepository;

    @Transactional(readOnly = true)
    public QueryBoardDetailsResponse execute(Long id) {
        Board board = boardFacade.getBoardById(id);

        return QueryBoardDetailsResponse.builder()
                .boardId(board.getId())
                .imageUrl(imageUrl(board))
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getUser().getAccountId())
                .date(board.getCreatedAt())
                .build();
    }

    private List<String> imageUrl(Board board) {

        return boardImageRepository.findByBoard(board)
                .stream()
                .map(BoardImage::getImgUrl)
                .toList();
    }
}
