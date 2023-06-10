package com.example.rado.domain.user.service;

import com.example.rado.domain.user.controller.dto.request.SignRequest;
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
                            .password(request.getPassword())
                            .name(request.getName())
                            .build());
        }
        else
            throw new AlreadyExistIdException();
    }

    @Transactional
    public void modifyUser(
            SignRequest request,
            Long id
    ) {
        User user = userRepository.findById(id)
                        .orElseThrow();

        user.update(request.getUserId(), request.getPassword(), request.getName());
    }

    @Transactional
    public void removeUser(
            Long id
    ) {
        userRepository.deleteById(id);
    }
}
