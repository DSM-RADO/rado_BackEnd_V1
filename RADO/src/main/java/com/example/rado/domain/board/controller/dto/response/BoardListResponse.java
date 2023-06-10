package com.example.rado.domain.board.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BoardListResponse {
    private List<BoardResponse> boardList;
}
