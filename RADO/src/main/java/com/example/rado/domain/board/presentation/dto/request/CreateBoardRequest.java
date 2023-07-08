package com.example.backend.domain.board.presentation.dto.request;

import com.example.backend.domain.board.enums.Category;
import lombok.Getter;

@Getter
public class CreateBoardRequest {
    private String title;
    private String content;
    private Category category;
}
