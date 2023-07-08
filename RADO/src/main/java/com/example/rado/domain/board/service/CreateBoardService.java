package com.example.backend.domain.board.service;

import com.example.backend.domain.board.domain.Board;
import com.example.backend.domain.board.domain.BoardImage;
import com.example.backend.domain.board.domain.repository.BoardImageRepository;
import com.example.backend.domain.board.domain.repository.BoardRepository;
import com.example.backend.domain.board.presentation.dto.request.CreateBoardRequest;
import com.example.backend.domain.user.domain.User;
import com.example.backend.domain.user.facade.UserFacade;
import com.example.backend.infrastructure.aws.image.s3.S3Upload;
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
    private final S3Upload s3Upload;
    private final BoardImageRepository boardImageRepository;

    @Transactional
    public void execute(List<MultipartFile> images, CreateBoardRequest request) {
        User user = userFacade.getCurrentUser();

        Board post = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .user(user).build();

        boardRepository.save(post);

        if (images != null) {
            images.stream()
                    .map(s3Upload::uploadImage)
                    .map(image -> BoardImage.builder()
                            .board(post)
                            .imgUrl(image)
                            .build()
                    )
                    .forEach(boardImageRepository::save);
        }
    }
}
