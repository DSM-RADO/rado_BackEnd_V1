package com.example.backend.domain.comment.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentAddRequest {
    private String comment;
    private String writer;
}
