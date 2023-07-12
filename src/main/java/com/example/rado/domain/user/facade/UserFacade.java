package com.example.rado.domain.user.facade;

import com.example.rado.domain.user.domain.User;
import com.example.rado.domain.user.domain.repository.UserRepository;
import com.example.rado.domain.user.exception.UserNotFoundException;
import com.example.rado.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByUserId(userId);
    }

    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
