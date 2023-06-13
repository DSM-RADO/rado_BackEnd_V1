package com.example.rado.domain.board.service;


import com.example.rado.domain.board.controller.dto.request.BoardAddRequest;
import com.example.rado.domain.board.controller.dto.request.BoardCommentAddRequest;
import com.example.rado.domain.board.controller.dto.request.BoardCommentRequest;
import com.example.rado.domain.board.entity.Board;
import com.example.rado.domain.board.entity.BoardComment;
import com.example.rado.domain.board.repository.BoardCommentRepository;
import com.example.rado.domain.board.repository.BoardRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class BoardCommentService {

    private final BoardRepository boardRepository;

    private final BoardCommentRepository boardCommentRepository;

    public void commentAdd(
            BoardCommentAddRequest request
    ){
        Board board = boardRepository.findByContent(request.getContent())
                        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다"));

        boardCommentRepository.save(
                BoardComment.builder()
                        .board(board)
                        .comment(request.getComment())
                        .build());
    }

    public void commentRemove(
            Long id
    ) {
        boardCommentRepository.deleteById(id);
    }
}
