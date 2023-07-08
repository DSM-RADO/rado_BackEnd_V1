package com.example.backend.domain.board.service;

import com.example.backend.domain.board.domain.Board;
import com.example.backend.domain.board.enums.Category;
import com.example.backend.domain.board.facade.BoardFacade;
import com.example.backend.domain.board.presentation.dto.response.QueryBoardListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryBoardCategoryListService {
    private final BoardFacade boardFacade;

    @Transactional(readOnly = true)
    public List<QueryBoardListResponse> execute(Category category) {

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Board> boards = boardFacade.getBoardAllById(sort);

        return boards.stream()
                .filter(board -> board.getCategory().equals(category))
                .map(board -> QueryBoardListResponse.builder()
                        .boardId(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .date(board.getCreatedAt())
                        .writer(board.getUser().getNickName())
                        .build())
                .toList();
    }
}
