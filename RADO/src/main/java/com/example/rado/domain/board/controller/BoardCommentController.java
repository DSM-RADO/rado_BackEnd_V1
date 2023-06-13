package com.example.rado.domain.board.controller;

import com.example.rado.domain.board.controller.dto.request.BoardCommentAddRequest;
import com.example.rado.domain.board.controller.dto.request.BoardCommentRequest;
import com.example.rado.domain.board.service.BoardCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/comment")
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @PostMapping
    public void addComment(
            @RequestBody @Valid BoardCommentAddRequest request
            ) {
        boardCommentService.commentAdd(request);
    }
}
