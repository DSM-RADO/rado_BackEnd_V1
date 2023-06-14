package com.example.rado.domain.board.service;

import com.example.rado.domain.board.controller.dto.request.BoardAddRequest;
import com.example.rado.domain.board.controller.dto.request.BoardRequest;
import com.example.rado.domain.board.entity.Board;
import com.example.rado.domain.board.repository.BoardRepository;
import com.example.rado.domain.user.entity.User;
import com.example.rado.domain.user.repository.UserRepository;
import com.example.rado.domain.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    private final UserFacade userFacade;

    @Transactional
    public void boardAdd(
            BoardAddRequest request
    ) {
        User curUser = userFacade.currentUser();

        boardRepository.save(
                Board.builder()
                        .user(curUser)
                        .content(request.getContent())
                        .build());
    }

    @Transactional
    public void boardRemove(
            Long id
    ) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void boardModify(
            BoardRequest request,
            Long id
    ) {
        Board board = boardRepository.findById(id)
                .orElseThrow();

        board.update(request.getContent());
    }


}
