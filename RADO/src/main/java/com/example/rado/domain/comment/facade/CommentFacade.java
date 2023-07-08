package com.example.backend.domain.comment.facade;

import com.example.backend.domain.board.domain.Board;
import com.example.backend.domain.comment.domain.Comment;
import com.example.backend.domain.comment.domain.repository.CommentRepository;
import com.example.backend.global.error.ErrorCode;
import com.example.backend.global.error.exeception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentFacade {

    private final CommentRepository commentRepository;

    public Comment findCommentById(Long id){
        return commentRepository.findCommentById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
    }

    public List<Comment> getCommentAllById(Sort sort){
        return commentRepository.findAll(sort);
    }
}
