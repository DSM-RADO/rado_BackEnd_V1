package com.example.rado.domain.user.service;

import com.example.rado.domain.user.domain.User;
import com.example.rado.domain.user.domain.repository.UserRepository;
import com.example.rado.domain.user.exception.UserNotFoundException;
import com.example.rado.domain.user.facade.UserFacade;
import com.example.rado.domain.user.presentation.dto.response.InfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InfoService {

    private final UserRepository userRepository;
    private final UserFacade userFacade;

    public InfoResponse userInfo(){

        User currentUser  = userFacade.getCurrentUser();
        Optional<User>user = userRepository.findByUserId(currentUser.getUserId());

        if(!user.isPresent()){
            throw UserNotFoundException.EXCEPTION;
        }
            return new InfoResponse(user.get().getUserId(), user.get().getNickname());
    }
}
