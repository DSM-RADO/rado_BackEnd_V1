package com.example.backend.domain.comment.presentation;

import com.example.backend.domain.comment.presentation.dto.request.CommentAddRequest;
import com.example.backend.domain.comment.presentation.dto.request.CommentRequest;
import com.example.backend.domain.comment.presentation.dto.response.CommentListResponse;
import com.example.backend.domain.comment.service.CreateCommentService;
import com.example.backend.domain.comment.service.DeleteCommentService;
import com.example.backend.domain.comment.service.ListCommentService;
import com.example.backend.domain.comment.service.ModifyCommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentController {

    private final CreateCommentService createCommentService;
    private final DeleteCommentService deleteCommentService;
    private final ListCommentService listCommentService;
    private final ModifyCommentService modifyCommentService;

    @Operation(summary = "댓글 생성")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("{boardId}")
    public void createComment(@RequestBody CommentAddRequest request, @PathVariable Long boardId){
        createCommentService.commentAdd(request, boardId);
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/delete/{boardId}/{commentId}")
    public void deleteComment(@PathVariable Long boardId, @PathVariable Long commentId){
        deleteCommentService.commentDelete(boardId, commentId);
    }

    @Operation(summary = "댓글 조회")
    @GetMapping("/{boardId}/list")
    public List<CommentListResponse> listComment(@PathVariable Long boardId){
        return listCommentService.commentList(boardId);
    }

    @Operation(summary = "댓글 수정")
    @PutMapping("/modify/{boardId}/{commentId}")
    public void modifyComment(@RequestBody CommentRequest request, @PathVariable Long boardId, @PathVariable Long commentId){
        modifyCommentService.commentModify(request, boardId, commentId);
    }

}
