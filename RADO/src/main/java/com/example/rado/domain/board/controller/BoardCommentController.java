package com.example.rado.domain.board.controller;

import com.example.rado.domain.board.controller.dto.request.BoardCommentAddRequest;
import com.example.rado.domain.board.controller.dto.request.BoardCommentRequest;
import com.example.rado.domain.board.service.BoardCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/remove/{commentId}")
    public void removeComment(
            @PathVariable Long commentId
    ) {
        boardCommentService.commentRemove(commentId);
    }
}
