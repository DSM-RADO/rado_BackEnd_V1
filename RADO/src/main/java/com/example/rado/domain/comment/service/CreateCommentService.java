package com.example.backend.domain.comment.service;

import com.example.backend.domain.board.domain.Board;
import com.example.backend.domain.board.facade.BoardFacade;
import com.example.backend.domain.comment.domain.Comment;
import com.example.backend.domain.comment.domain.repository.CommentRepository;
import com.example.backend.domain.comment.presentation.dto.request.CommentAddRequest;
import com.example.backend.domain.user.domain.User;
import com.example.backend.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCommentService {

    private final BoardFacade boardFacade;
    private final UserFacade userFacade;
    private final CommentRepository commentRepository;

    @Transactional
    public void commentAdd(CommentAddRequest request, Long boardId){

        User user = userFacade.getCurrentUser();
        Board board = boardFacade.getBoardById(boardId);

        commentRepository.save(
                Comment.builder()
                        .writer(user.getNickName())
                        .comment(request.getComment())
                        .board(board)
                        .user(user)
                        .build());
    }
}
