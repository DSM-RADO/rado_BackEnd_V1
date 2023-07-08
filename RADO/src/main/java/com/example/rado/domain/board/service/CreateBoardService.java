package com.example.rado.domain.board.service;

import com.example.rado.domain.board.domain.Board;
import com.example.rado.domain.board.domain.repository.BoardRepository;
import com.example.rado.domain.board.presentation.dto.request.CreateBoardRequest;
import com.example.rado.domain.user.domain.User;
import com.example.rado.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateBoardService {
    private final UserFacade userFacade;
    private final BoardRepository boardRepository;

    @Transactional
    public void execute(CreateBoardRequest request) {
        User user = userFacade.getCurrentUser();

        Board post = Board.builder()
                .content(request.getContent())
                .user(user).build();

        boardRepository.save(post);
        }
    }
