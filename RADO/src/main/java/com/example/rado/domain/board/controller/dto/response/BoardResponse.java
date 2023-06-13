package com.example.rado.domain.board.controller.dto.response;

import com.example.rado.domain.board.entity.Board;
import com.example.rado.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BoardResponse {

    private String comment;

    private String content;

    private User user;

    public static BoardResponse of(Board board){
        return BoardResponse.builder()
                .user(board.getUser())
                .content(board.getContent())
                .build();
    }
}
