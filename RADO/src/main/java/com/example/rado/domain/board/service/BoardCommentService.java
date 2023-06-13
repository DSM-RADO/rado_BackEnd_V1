package com.example.rado.domain.board.service;


import com.example.rado.domain.board.controller.dto.request.BoardCommentAddRequest;
import com.example.rado.domain.board.controller.dto.request.BoardCommentRequest;
import com.example.rado.domain.board.entity.Board;
import com.example.rado.domain.board.entity.BoardComment;
import com.example.rado.domain.board.repository.BoardCommentRepository;
import com.example.rado.domain.board.repository.BoardRepository;
import com.example.rado.domain.user.entity.User;
import com.example.rado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardCommentService {

    private final BoardRepository boardRepository;

    private final BoardCommentRepository boardCommentRepository;

    private final UserRepository userRepository;

    @Transactional
    public void commentAdd(
            BoardCommentAddRequest request
    ){
        Board board = boardRepository.findByContent(request.getContent())
                        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다"));

        User user = userRepository.findByUserId(request.getUserId())
                        .orElseThrow(() -> new  IllegalArgumentException("해당 아이디가 없습니다"));

        boardCommentRepository.save(
                BoardComment.builder()
                        .board(board)
                        .user(user)
                        .comment(request.getComment())
                        .build());
    }

    @Transactional
    public void commentRemove(
            Long id
    ) {
        boardCommentRepository.deleteById(id);
    }

    @Transactional
    public void commentModify(
            BoardCommentRequest request,
            Long id
    ) {
        BoardComment boardComment = boardCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다"));

        boardComment.update(request.getComment());
    }

}
