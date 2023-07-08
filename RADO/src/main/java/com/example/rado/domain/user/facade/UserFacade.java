package com.example.rado.domain.user.facade;

import com.example.rado.domain.user.domain.User;
import com.example.rado.domain.user.domain.repository.UserRepository;
import com.example.rado.global.error.ErrorCode;
import com.example.rado.global.error.exeception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByaccountId(accountId);
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

    }
    public User getUserByaccountId(String accountId) {
        return userRepository.findUserByUserId(accountId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    public boolean existsByAccountId(String accountId){
        if(userRepository.existsByUserId(accountId)){
            throw new CustomException(ErrorCode.EXIST_USER);
        }
        return true;
    }

    public User getUserByAccountId(String accountId) {
        return userRepository.findUserByUserId(accountId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
