package com.example.rado.domain.board.service;

import com.example.rado.domain.board.domain.Board;
import com.example.rado.domain.board.domain.repository.BoardRepository;
import com.example.rado.domain.board.facade.BoardFacade;
import com.example.rado.domain.board.presentation.dto.request.UpdateBoardRequest;
import com.example.rado.domain.user.domain.User;
import com.example.rado.domain.user.facade.UserFacade;
import com.example.rado.global.error.ErrorCode;
import com.example.rado.global.error.exeception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModifyBoardService {
    private final UserFacade userFacade;
    private final BoardRepository boardRepository;
    private final BoardFacade boardFacade;

    @Transactional
    public void execute(UpdateBoardRequest request, Long postId) {
        User user = userFacade.getCurrentUser();

        Board board = boardFacade.getBoardById(postId);

        if (!user.getAccountId().equals(board.getUser().getAccountId())) {
            throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
        }



        board.updatePost(request.getContent());
        boardRepository.save(board);
    }
}
