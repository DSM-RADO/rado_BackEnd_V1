package com.example.backend.domain.comment.service;

import com.example.backend.domain.board.domain.Board;
import com.example.backend.domain.board.facade.BoardFacade;
import com.example.backend.domain.comment.domain.Comment;
import com.example.backend.domain.comment.facade.CommentFacade;
import com.example.backend.domain.comment.presentation.dto.response.CommentListResponse;
import com.example.backend.domain.user.domain.User;
import com.example.backend.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListCommentService {

    private final CommentFacade commentFacade;
    private final UserFacade userFacade;
    private final BoardFacade boardFacade;

    @Transactional(readOnly = true)
    public List<CommentListResponse> commentList(Long boardId) {

        User user = userFacade.getCurrentUser();

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Comment> comments = commentFacade.getCommentAllById(sort);

        return comments.stream()
                .filter(comment -> comment.getBoard().getId().equals(boardId))
                .map(comment -> CommentListResponse.builder()
                        .commentId(comment.getId())
                        .comment(comment.getComment())
                        .writer(user.getNickName())
                        .date(comment.getCreatedAt())
                        .build())
                .toList();
    }
}
