package com.example.rado.domain.user.service;

import com.example.rado.domain.user.domain.User;
import com.example.rado.domain.user.domain.repository.UserRepository;
import com.example.rado.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveService {

    private final UserRepository userRepository;
    private final UserFacade userFacade;

    public void userRemove(){

        User user = userFacade.getCurrentUser();

        userRepository.delete(user);
    }
}
