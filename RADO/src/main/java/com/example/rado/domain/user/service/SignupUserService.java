package com.example.backend.domain.user.service;

import com.example.backend.domain.user.domain.User;
import com.example.backend.domain.user.domain.repository.UserRepository;
import com.example.backend.domain.user.facade.UserFacade;
import com.example.backend.domain.user.presentation.dto.request.SignUpRequest;
import com.example.backend.global.error.ErrorCode;
import com.example.backend.global.error.exeception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserFacade userFacade;

    @Transactional
    public void execute(SignUpRequest request) {

        userFacade.existsByAccountId(request.getAccountId());

        if (!request.getPassword().equals(request.getPasswordValid())) {
            throw new CustomException(ErrorCode.PASSWORD_MISS_MATCHED);
        }

        userRepository.save(User.builder()
                .email(request.getEmail())
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickName(request.getAccountId())
                .build());
    }
}
