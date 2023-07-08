package com.example.rado.domain.user.service.Faeade;

import com.example.rado.domain.user.entity.User;
import com.example.rado.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User currentUser(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디 입니다"));
    }

    public boolean  existsByUserId(String userId) {
        if (userRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException("중복된 아이디 입니다");
        }
        else return true;
    }

    public User getUserByUserId(String userId){
       return userRepository.findUserByUserId(userId)
               .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다"));
    }
}
