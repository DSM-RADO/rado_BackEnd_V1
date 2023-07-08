package com.example.rado.domain.board.service;

import com.example.rado.domain.board.domain.Board;
import com.example.rado.domain.board.facade.BoardFacade;
import com.example.rado.domain.board.presentation.dto.response.QueryBoardDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class QueryBoardDetailService {
    private final BoardFacade boardFacade;

    @Transactional(readOnly = true)
    public QueryBoardDetailsResponse execute(Long id) {
        Board board = boardFacade.getBoardById(id);

        return QueryBoardDetailsResponse.builder()
                .boardId(board.getId())
                .content(board.getContent())
                .writer(board.getUser().getAccountId())
                .date(board.getCreatedAt())
                .build();
    }

}
