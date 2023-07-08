package com.example.backend.domain.board.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class QueryBoardDetailsResponse {
    private Long boardId;
    private String writer;
    private String title;
    private String content;
    private LocalDate date;
    private List<String> imageUrl;
}
