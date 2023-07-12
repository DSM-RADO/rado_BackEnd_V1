package com.example.rado.domain.user.service;

import com.example.rado.domain.user.domain.User;
import com.example.rado.domain.user.domain.repository.UserRepository;
import com.example.rado.domain.user.presentation.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void userSignUp(SignUpRequest request){

        userRepository.save(
                User.builder()
                        .userId(request.getUserId())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .nickname(request.getNickname())
                        .build());
    }
}
