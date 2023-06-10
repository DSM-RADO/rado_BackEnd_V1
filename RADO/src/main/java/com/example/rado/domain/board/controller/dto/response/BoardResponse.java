package com.example.rado.domain.board.controller.dto.response;

import com.example.rado.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BoardResponse {

    private String content;

    public static BoardResponse of(Board board){
        return BoardResponse.builder()
                .content(board.getContent())
                .build();
    }
}
