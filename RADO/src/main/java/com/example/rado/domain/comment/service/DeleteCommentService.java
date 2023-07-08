package com.example.rado.domain.comment.service;

import com.example.rado.domain.board.domain.Board;
import com.example.rado.domain.board.facade.BoardFacade;
import com.example.rado.domain.comment.domain.Comment;
import com.example.rado.domain.comment.domain.repository.CommentRepository;
import com.example.rado.domain.comment.facade.CommentFacade;
import com.example.rado.domain.user.domain.User;
import com.example.rado.domain.user.facade.UserFacade;
import com.example.rado.global.error.ErrorCode;
import com.example.rado.global.error.exeception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCommentService {

    private final BoardFacade boardFacade;
    private final UserFacade userFacade;
    private final CommentFacade commentFacade;
    private final CommentRepository commentRepository;

    @Transactional
    public void commentDelete(Long boardId, Long commentId){

        User user = userFacade.getCurrentUser();
        Board board = boardFacade.getBoardById(boardId);
        Comment comment = commentFacade.findCommentById(commentId);

        if (!user.getAccountId().equals(board.getUser().getAccountId())) {
            throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
        }

        commentRepository.delete(comment);
    }
}
