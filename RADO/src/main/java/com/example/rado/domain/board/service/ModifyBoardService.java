package com.example.backend.domain.board.service;

import com.example.backend.domain.board.domain.Board;
import com.example.backend.domain.board.domain.BoardImage;
import com.example.backend.domain.board.domain.repository.BoardImageRepository;
import com.example.backend.domain.board.domain.repository.BoardRepository;
import com.example.backend.domain.board.facade.BoardFacade;
import com.example.backend.domain.board.presentation.dto.request.UpdateBoardRequest;
import com.example.backend.domain.user.domain.User;
import com.example.backend.domain.user.facade.UserFacade;
import com.example.backend.global.error.ErrorCode;
import com.example.backend.global.error.exeception.CustomException;
import com.example.backend.infrastructure.aws.image.s3.S3Upload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModifyBoardService {
    private final UserFacade userFacade;
    private final BoardRepository boardRepository;
    private final BoardFacade boardFacade;
    private final S3Upload s3Upload;
    private final BoardImageRepository boardImageRepository;

    @Transactional
    public void execute(List<MultipartFile> images, UpdateBoardRequest request, Long postId) {
        User user = userFacade.getCurrentUser();

        Board board = boardFacade.getBoardById(postId);

        if (!user.getEmail().equals(board.getUser().getEmail())) {
            throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
        }

        boardImageRepository.deleteAllByBoard(board);

        if (images != null) {
            images.stream()
                    .map(s3Upload::uploadImage)
                    .map(image -> BoardImage.builder()
                            .board(board)
                            .imgUrl(image)
                            .build()
                    )
                    .forEach(boardImageRepository::save);
        }

        board.updatePost(request.getTitle(), request.getContent());
        boardRepository.save(board);
    }
}
