package com.example.backend.domain.user.service;

import com.example.backend.domain.user.domain.User;
import com.example.backend.domain.user.domain.repository.UserRepository;
import com.example.backend.domain.user.facade.UserFacade;
import com.example.backend.infrastructure.aws.image.s3.S3Upload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteUserService {

    private final UserFacade userFacade;
    private final S3Upload s3Upload;
    private final UserRepository userRepository;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();

        s3Upload.delUser(user);
        userRepository.delete(user);
    }
}
