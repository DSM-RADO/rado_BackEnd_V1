package com.example.rado.domain.user.service;

import com.example.rado.domain.user.controller.dto.request.SignupRequest;
import com.example.rado.domain.user.entity.User;
import com.example.rado.domain.user.repository.UserRepository;
import com.example.rado.domain.user.service.exception.AlreadyExistIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void userAdd(
        SignupRequest request
    ) {
        String userId = request.getUserId();

        Optional<User>user = userRepository.findByUserId(userId);
        if(user.isEmpty()){
            userRepository.save(
                    User.builder()
                            .userId(request.getUserId())
                            .userPassword(request.getUserPassword())
                            .userName(request.getUserName())
                            .build());
        }
        else
            throw new AlreadyExistIdException();
    }
}
