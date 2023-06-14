package com.example.rado.domain.user.service;

import com.example.rado.domain.user.entity.User;
import com.example.rado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User currentUser(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUserId(userId)
                .orElseThrow(()-> new EntityNotFoundException("찾을 수 없는 유저 입니다."));
    }
}
