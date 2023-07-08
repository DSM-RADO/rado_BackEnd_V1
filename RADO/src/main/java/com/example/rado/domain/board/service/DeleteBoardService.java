package com.example.backend.domain.board.service;

import com.example.backend.domain.board.domain.Board;
import com.example.backend.domain.board.domain.repository.BoardRepository;
import com.example.backend.domain.board.facade.BoardFacade;
import com.example.backend.domain.user.domain.User;
import com.example.backend.domain.user.facade.UserFacade;
import com.example.backend.global.error.ErrorCode;
import com.example.backend.global.error.exeception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteBoardService {

    private final UserFacade userFacade;
    private final BoardFacade boardFacade;
    private final BoardRepository boardRepository;

    @Transactional
    public void execute(Long id) {
        User user = userFacade.getCurrentUser();
        Board board = boardFacade.getBoardById(id);

        if(!user.getEmail().equals(board.getUser().getEmail())){
            throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
        }

        boardRepository.delete(board);
    }
}
