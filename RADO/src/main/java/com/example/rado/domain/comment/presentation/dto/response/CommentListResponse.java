package com.example.backend.domain.comment.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class CommentListResponse {
    private Long commentId;
    private String comment;
    private String writer;
    private LocalDate date;
}
