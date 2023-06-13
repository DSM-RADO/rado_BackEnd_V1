package com.example.rado.domain.board.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardCommentAddRequest {

    private String comment;

    private String content;

    private String userId;
}
