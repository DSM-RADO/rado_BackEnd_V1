package com.example.rado.domain.user.service;

import com.example.rado.domain.user.domain.User;
import com.example.rado.domain.user.domain.repository.UserRepository;
import com.example.rado.domain.user.facade.UserFacade;
import com.example.rado.domain.user.presentation.dto.request.SignUpRequest;
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

        userRepository.save(User.builder()
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickName(request.getAccountId())
                .build());
    }
}
