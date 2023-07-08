package com.example.rado.domain.board.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class QueryBoardListResponse {
    private Long boardId;
    private String writer;
    private String content;
    private LocalDate date;
}
